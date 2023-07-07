package ken.projects.infit.features.feature_auth.presentation.login.events.validation

sealed class LoginValidationEvent {

   object InputValidation: LoginValidationEvent()
   object FormValidation: LoginValidationEvent()

}