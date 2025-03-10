package ru.mobileup.samples.features.otp.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.samples.core.timer.TimerState
import ru.mobileup.samples.core.widget.text_field.otpFakeInputControl

class FakeOtpComponent : OtpComponent {
    override val confirmationCodeInputControl = otpFakeInputControl()
    override val timerState = MutableStateFlow(TimerState.Finish)
    override val sendCodeEnable = MutableStateFlow(true)
    override val isConfirmCodeCorrect = MutableStateFlow(false)
    override val isCodeResendInProgress = MutableStateFlow(false)
    override val isConfirmationInProgress = MutableStateFlow(false)
    override fun onResendCodeClick() = Unit
    override fun resetState() = Unit
}