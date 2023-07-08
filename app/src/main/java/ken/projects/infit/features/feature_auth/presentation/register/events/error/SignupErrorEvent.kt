package ken.projects.infit.features.feature_auth.presentation.register.events.error

sealed class SignupErrorEvent() {

    data class FailedSignUp(val message: String) : SignupErrorEvent()
    object InvalidEmail : SignupErrorEvent()
    object InvalidPassword : SignupErrorEvent()
    object PasswordsNotMatching : SignupErrorEvent()
    object InvalidUserName : SignupErrorEvent()

}
