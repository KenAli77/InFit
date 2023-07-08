package ken.projects.infit.features.feature_auth.presentation.login.events.error

sealed class LoginErrorEvent {

    data class FailedLogin(val message:String):LoginErrorEvent()
    data class InvalidField(val field:String):LoginErrorEvent()

}