package ken.projects.infit.features.auth.presentation.register.events.user_input


sealed class SignUpUserInputEvent {

    data class EnteredUserName(val userName:String): SignUpUserInputEvent()
    data class EnteredEmail(val email:String): SignUpUserInputEvent()
    data class EnteredPassword(val password:String): SignUpUserInputEvent()
    data class EnteredConfirmPassword(val confirmPassword:String): SignUpUserInputEvent()

}