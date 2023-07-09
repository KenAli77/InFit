package ken.projects.infit.features.feature_auth.presentation.register.events.validation


sealed class SignUpValidationEvent {

    object Success: SignUpValidationEvent()

}