package ken.projects.infit.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ken.projects.infit.ui.composables.login.LoginScreen
import ken.projects.infit.ui.composables.signup.SignUpScreen
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    loginViewModel: LoginViewModel,
    scaffoldState: ScaffoldState
) {

    navigation(startDestination = Screens.Login.route, route = LOGIN_ROUTE)
    {
        composable(route = Screens.Login.route){
            LoginScreen(navController,loginViewModel,scaffoldState)
            bottomBarState.value = false
        }
        composable(route = Screens.Signup.route){
            SignUpScreen(navController,loginViewModel,scaffoldState)
            bottomBarState.value = false
        }
    }
}