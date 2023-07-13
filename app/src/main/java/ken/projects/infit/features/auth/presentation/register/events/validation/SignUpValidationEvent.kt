package ken.projects.infit.features.auth.presentation.register.events.validation


sealed class SignUpValidationEvent {

    object Success: SignUpValidationEvent()

}