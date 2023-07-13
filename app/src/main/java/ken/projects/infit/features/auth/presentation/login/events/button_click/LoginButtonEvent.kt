package ken.projects.infit.features.auth.presentation.login.events.button_click

sealed class LoginButtonEvent {

    object LoginButtonClick: LoginButtonEvent()
    object SignUpButtonClick: LoginButtonEvent()
    object ForgotPasswordButtonClick: LoginButtonEvent()

}