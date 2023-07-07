package ken.projects.infit.features.feature_auth.presentation.login.events.navigation

sealed class LoginNavigationEvent {

    object NavigateToSignup: LoginNavigationEvent()
    object NavigateToForgotPassword: LoginNavigationEvent()
}