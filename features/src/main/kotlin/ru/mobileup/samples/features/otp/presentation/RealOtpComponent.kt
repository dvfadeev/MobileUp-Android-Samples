package ru.mobileup.samples.features.otp.presentation

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.desc.strResDesc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import ru.mobileup.kmm_form_validation.options.ImeAction
import ru.mobileup.samples.core.error_handling.ErrorHandler
import ru.mobileup.samples.core.error_handling.safeLaunch
import ru.mobileup.samples.core.timer.Timer
import ru.mobileup.samples.core.timer.isTicking
import ru.mobileup.samples.core.utils.componentScope
import ru.mobileup.samples.core.utils.computed
import ru.mobileup.samples.core.utils.numberInputControl
import ru.mobileup.samples.core.utils.withProgress
import ru.mobileup.samples.features.R

class RealOtpComponent(
    componentContext: ComponentContext,
    private val errorHandler: ErrorHandler,
) : ComponentContext by componentContext, OtpComponent {

    companion object {
        private const val RESEND_CODE_AGAIN_TIMER_LOCK = 10L
        private const val CONFIRMATION_CODE_LENGTH = 4
    }

    override val confirmationCodeInputControl = numberInputControl(
        maxLength = CONFIRMATION_CODE_LENGTH,
        imeAction = ImeAction.None
    )

    private val timer = Timer(componentScope)

    override val timerState = timer.timerState

    override val isConfirmCodeCorrect = MutableStateFlow(false)

    override val isCodeResendInProgress = MutableStateFlow(false)

    override val isConfirmationInProgress = MutableStateFlow(false)

    override val sendCodeEnable = computed(
        timerState,
        isConfirmCodeCorrect,
        isConfirmationInProgress
    ) { timerState, isCodeSendingInProgress, isConfirmationInProgress ->
        !timerState.isTicking() && !isCodeSendingInProgress && !isConfirmationInProgress
    }

    private val codeLengthIsCorrect = confirmationCodeInputControl
        .text
        .map { it.length == CONFIRMATION_CODE_LENGTH }
        .stateIn(
            scope = componentScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    init {
        timer.start(RESEND_CODE_AGAIN_TIMER_LOCK)

        // Чтобы убрать ошибку после изменения введенного кода
        confirmationCodeInputControl.text
            .onEach { confirmationCodeInputControl.error.value = null }
            .launchIn(componentScope)

        computed(
            codeLengthIsCorrect,
            confirmationCodeInputControl.error
        ) { lengthIsCorrect, error ->
            lengthIsCorrect && error == null
        }.onEach { isValid ->
            if (isValid) {
                sendCode()
            }
        }.launchIn(componentScope)

        computed(
            isCodeResendInProgress,
            isConfirmationInProgress,
            isConfirmCodeCorrect
        ) { isCodeSendingInProgress, isConfirmationInProgress, isCodeCorrect ->
            isCodeSendingInProgress || isConfirmationInProgress || isCodeCorrect
        }.onEach { enterFieldDisable ->
            confirmationCodeInputControl.enabled.value = !enterFieldDisable
        }.launchIn(componentScope)

        confirmationCodeInputControl.onFocusChanged(true)
    }

    private fun sendCode() {
        componentScope.safeLaunch(errorHandler) {
            withProgress(isConfirmationInProgress) {
                delay(2000)
                if (confirmationCodeInputControl.text.value == "1234") {
                    isConfirmCodeCorrect.value = true
                } else {
                    confirmationCodeInputControl.setText("")
                    confirmationCodeInputControl.error.value =
                        R.string.otp_error_code.strResDesc()
                    confirmationCodeInputControl.onFocusChanged(true)
                }
            }
        }
    }

    override fun onResendCodeClick() {
        componentScope.safeLaunch(errorHandler) {
            withProgress(isCodeResendInProgress) {
                delay(2000)
                timer.start(RESEND_CODE_AGAIN_TIMER_LOCK)
                confirmationCodeInputControl.onFocusChanged(true)
            }
        }
    }

    override fun resetState() {
        timer.start(RESEND_CODE_AGAIN_TIMER_LOCK)
        isConfirmCodeCorrect.value = false
        confirmationCodeInputControl.setText("")
        confirmationCodeInputControl.error.value = null
        confirmationCodeInputControl.onFocusChanged(true)
    }
}