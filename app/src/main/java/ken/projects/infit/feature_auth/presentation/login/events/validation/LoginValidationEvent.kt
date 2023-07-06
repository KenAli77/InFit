package ken.projects.infit.feature_auth.presentation.login.events.validation

sealed class LoginValidationEvent {

   object InputValidation:LoginValidationEvent()
   object FormValidation:LoginValidationEvent()

}