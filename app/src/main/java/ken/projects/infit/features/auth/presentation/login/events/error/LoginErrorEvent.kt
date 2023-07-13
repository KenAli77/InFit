package ken.projects.infit.features.auth.presentation.login.events.error

sealed class LoginErrorEvent {

    data class FailedLogin(val message:String):LoginErrorEvent()
    data class InvalidField(val field:String):LoginErrorEvent()

}