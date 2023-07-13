package ken.projects.infit.features.auth.presentation.login.events.navigation

import ken.projects.infit.core.navigation.MAIN_ROUTE
import ken.projects.infit.core.navigation.Screens


sealed class LoginNavigationEvent(val route: String) {

    object NavigateToSignup: LoginNavigationEvent(route = Screens.Signup.route)
    object NavigateToForgotPassword: LoginNavigationEvent(route = "")
    object NavigateToMain : LoginNavigationEvent(route = MAIN_ROUTE)
}