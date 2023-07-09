package ken.projects.infit.core.navigation

import androidx.compose.material.ScaffoldState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ken.projects.infit.features.feature_auth.presentation.login.components.LoginScreen
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel
import ken.projects.infit.features.feature_auth.presentation.register.components.SignUpScreen
import ken.projects.infit.features.feature_auth.presentation.register.viewmodel.SignUpViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    navigation(startDestination = Screens.Login.route, route = AUTH_ROUTE)
    {
        composable(route = Screens.Login.route){

            val viewModel = hiltViewModel<LoginViewModel>(it)
            LoginScreen(navController,viewModel,scaffoldState)
        }
        composable(route = Screens.Signup.route){

            val viewModel = hiltViewModel<SignUpViewModel>(it)
            SignUpScreen(navController, viewModel,scaffoldState)
        }
    }
}