package ken.projects.infit.features.auth.presentation.login.events.user_input

sealed class LoginUserInputEvent {

    data class EnteredEmail(val email:String):LoginUserInputEvent()
    data class EnteredPassword(val password:String):LoginUserInputEvent()
}