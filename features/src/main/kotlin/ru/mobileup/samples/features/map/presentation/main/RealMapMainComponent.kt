package ru.mobileup.samples.features.map.presentation.main

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.desc.strResDesc
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import ru.mobileup.samples.core.app_settings.data.AppSettingsStorage
import ru.mobileup.samples.core.app_settings.domain.AppTheme
import ru.mobileup.samples.core.dialog.simple.SimpleDialogControl
import ru.mobileup.samples.core.dialog.simple.simpleDialogControl
import ru.mobileup.samples.core.dialog.standard.DialogButton
import ru.mobileup.samples.core.dialog.standard.StandardDialogData
import ru.mobileup.samples.core.dialog.standard.standardDialogControl
import ru.mobileup.samples.core.error_handling.ErrorHandler
import ru.mobileup.samples.core.error_handling.LocationNotAvailableException
import ru.mobileup.samples.core.error_handling.safeLaunch
import ru.mobileup.samples.core.external_apps.data.ExternalAppService
import ru.mobileup.samples.core.location.CLOSE_MAP_ZOOM
import ru.mobileup.samples.core.location.GeoCoordinate
import ru.mobileup.samples.core.location.LocationService
import ru.mobileup.samples.core.map.domain.MapCommand
import ru.mobileup.samples.core.map.domain.MapTheme
import ru.mobileup.samples.core.map.domain.MapVendor
import ru.mobileup.samples.core.permissions.PermissionService
import ru.mobileup.samples.core.permissions.SinglePermissionResult
import ru.mobileup.samples.core.theme.AppTheme
import ru.mobileup.samples.core.utils.componentScope
import ru.mobileup.samples.core.utils.observe
import ru.mobileup.samples.core.utils.withProgress
import ru.mobileup.samples.features.R
import ru.mobileup.samples.features.map.data.MapRepository

class RealMapMainComponent(
    componentContext: ComponentContext,
    override val vendor: MapVendor,
    private val permissionService: PermissionService,
    private val errorHandler: ErrorHandler,
    private val locationService: LocationService,
    mapRepository: MapRepository,
    private val externalAppService: ExternalAppService,
    settingsStorage: AppSettingsStorage,
    context: Context,
) : ComponentContext by componentContext, MapMainComponent {

    private val _mapCommands = Channel<MapCommand>()
    override val mapCommands = _mapCommands.receiveAsFlow()
    override val isCurrentLocationAvailable =
        MutableStateFlow(permissionService.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION))
    override val userCoordinate = MutableStateFlow<GeoCoordinate?>(null)

    override val isLocationSearchInProgress = MutableStateFlow(false)

    override val theme = MutableStateFlow(
        runBlocking {
            when (settingsStorage.getTheme()) {
                AppTheme.Light -> MapTheme.Bright
                AppTheme.Dark -> MapTheme.Dark
                AppTheme.System -> if (context.isSystemInDarkTheme()) MapTheme.Dark else MapTheme.Bright
            }
        }
    )

    private val placesReplica = mapRepository.placesReplica
    override val placesState = placesReplica.observe(this, errorHandler)

    override val placeDialogControl: SimpleDialogControl<GeoCoordinate> = simpleDialogControl(
        key = "placeDialogControl"
    )

    override val locationDialogControl = standardDialogControl(
        key = "locationDialogControl"
    )

    init {
        componentScope.safeLaunch(errorHandler) {
            placesState.firstOrNull { !it.data.isNullOrEmpty() }?.let {
                val moscowPlaces = GeoCoordinate.Companion.getPlacesInMoscowRegion(it.data ?: emptyList())
                _mapCommands.send(
                    MapCommand.MoveToBoundingBox(
                        coordinates = moscowPlaces,
                        animate = true
                    )
                )
            }
        }
    }

    override fun onZoomOutClick() {
        _mapCommands.trySend(MapCommand.ZoomOut)
    }

    override fun onZoomInClick() {
        _mapCommands.trySend(MapCommand.ZoomIn)
    }

    override fun onMyLocationClick() {
        componentScope.safeLaunch(errorHandler) {
            when (val result = permissionService.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                is SinglePermissionResult.Granted -> {
                    requestCurrentLocation()
                }

                is SinglePermissionResult.Denied -> {
                    isCurrentLocationAvailable.value = false
                    if (result.permanently && result.automatically) {
                        showLocationPermissionPermanentlyDeniedDialog()
                    }
                }
            }
        }
    }

    override fun onClusterClick(places: List<GeoCoordinate>) {
        _mapCommands.trySend(
            MapCommand.MoveToBoundingBox(
                coordinates = places,
                animate = true
            )
        )
    }

    override fun onPlaceClick(place: GeoCoordinate) {
        placeDialogControl.show(place)
    }

    override fun onThemeSwitch(newTheme: MapTheme) {
        componentScope.safeLaunch(errorHandler) {
            theme.value = newTheme
        }
    }

    override fun onRetryClick() {
        placesReplica.refresh()
    }

    private fun requestCurrentLocation() {
        componentScope.safeLaunch(errorHandler) {
            try {
                withProgress(isLocationSearchInProgress) {
                    val coordinate = locationService.getCurrentLocation()
                    isCurrentLocationAvailable.value = true
                    _mapCommands.send(MapCommand.MoveTo(coordinate, CLOSE_MAP_ZOOM))
                    userCoordinate.value = coordinate
                }
            } catch (_: LocationNotAvailableException) {
                isCurrentLocationAvailable.value = false
                showLocationDisabledDialog()
            }
        }
    }

    private fun showLocationPermissionPermanentlyDeniedDialog() {
        locationDialogControl.show(
            StandardDialogData(
                title = R.string.map_geo_permission_dialog_denied_title.strResDesc(),
                message = R.string.map_geo_permission_dialog_text.strResDesc(),
                confirmButton = DialogButton(
                    text = R.string.map_geo_permission_dialog_denied_confirm.strResDesc(),
                    action = {
                        externalAppService.openAppSettings()
                        locationDialogControl.dismiss()
                    }
                ),
                dismissButton = DialogButton(
                    text = R.string.map_geo_permission_dialog_denied_cancel.strResDesc(),
                    action = locationDialogControl::dismiss
                )
            )
        )
    }

    private fun showLocationDisabledDialog() {
        locationDialogControl.show(
            StandardDialogData(
                title = R.string.map_geo_permission_dialog_disabled_title.strResDesc(),
                message = R.string.map_geo_permission_dialog_text.strResDesc(),
                confirmButton = DialogButton(
                    text = R.string.map_geo_permission_dialog_disabled_confirm.strResDesc(),
                    action = {
                        externalAppService.openLocationSettings()
                        locationDialogControl.dismiss()
                    }
                ),
                dismissButton = DialogButton(
                    text = R.string.map_geo_permission_dialog_disabled_cancel.strResDesc(),
                    action = locationDialogControl::dismiss
                )
            )
        )
    }
}

private fun Context.isSystemInDarkTheme(): Boolean {
    val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}