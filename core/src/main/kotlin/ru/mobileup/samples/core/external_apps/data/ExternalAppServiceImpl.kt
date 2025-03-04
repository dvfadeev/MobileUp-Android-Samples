package ru.mobileup.samples.core.external_apps.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mobileup.samples.core.error_handling.ExternalAppNotFoundException
import ru.mobileup.samples.core.utils.e
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ExternalAppServiceImpl(
    private val context: Context
) : ExternalAppService {

    @Throws(ExternalAppNotFoundException::class)
    override fun openUrl(url: String) {
        safeActivityLaunch {
            val intent = if (url.startsWith("intent:")) {
                Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
            } else {
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            }

            intent.apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }

    @Throws(ExternalAppNotFoundException::class)
    override suspend fun openPdfFromAssets(fileName: String) {
        val file = copyFromAssetsToCache(fileName, context)
        val authority = "${context.packageName}.fileprovider"

        if (file != null) {
            val uri = FileProvider.getUriForFile(context, authority, file)

            safeActivityLaunch {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(uri, "application/pdf")
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
                intent
            }
        }
    }

    override suspend fun openPhone(phone: String) {
        safeActivityLaunch {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent
        }
    }

    @Throws(ExternalAppNotFoundException::class)
    override fun openAppSettings() {
        safeActivityLaunch {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }

    @Throws(ExternalAppNotFoundException::class)
    override fun openLocationSettings() {
        safeActivityLaunch {
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }

    override fun openNotificationSettings() {
        safeActivityLaunch {
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }

    private fun safeActivityLaunch(intent: () -> Intent) {
        try {
            ContextCompat.startActivity(context, intent(), null)
        } catch (e: ActivityNotFoundException) {
            throw ExternalAppNotFoundException(e)
        }
    }

    private suspend fun copyFromAssetsToCache(fileName: String, context: Context): File? {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(context.cacheDir, fileName)
                context.assets.open(fileName).use { inputStream ->
                    BufferedOutputStream(FileOutputStream(file)).use { outputStream ->
                        BufferedInputStream(inputStream).copyTo(outputStream)
                    }
                }
                return@withContext file
            } catch (e: IOException) {
                Logger.e(e)
                return@withContext null
            }
        }
    }
}
