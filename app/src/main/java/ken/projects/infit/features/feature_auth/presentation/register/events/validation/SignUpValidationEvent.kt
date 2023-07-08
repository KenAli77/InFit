package ken.projects.infit.features.feature_auth.presentation.register.events.validation


sealed class SignUpValidationEvent {

    object EmailValidation: SignUpValidationEvent()
    object PasswordValidation: SignUpValidationEvent()
    object ConfirmPasswordValidation: SignUpValidationEvent()
    object UserNameValidation : SignUpValidationEvent()

}