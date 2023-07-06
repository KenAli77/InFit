package ken.projects.infit.feature_auth.presentation.login.events.navigation

sealed class LoginNavigationEvent {

    object NavigateToSignup:LoginNavigationEvent()
    object NavigateToForgotPassword:LoginNavigationEvent()
}