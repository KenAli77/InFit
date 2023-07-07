package ken.projects.infit.features.feature_auth.presentation.login.events.button_click

sealed class LoginButtonEvent {

    object SignInButtonClick: LoginButtonEvent()
    object SignUpButtonClick: LoginButtonEvent()
    object ForgotPasswordButtonClick: LoginButtonEvent()

}