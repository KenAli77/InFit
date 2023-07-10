package ken.projects.infit.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import ken.projects.infit.ui.theme.darkBlue
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel
import ken.projects.infit.viewmodel.WorkoutViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
    workoutViewModel: WorkoutViewModel = viewModel(),
) {

    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val scaffoldState = rememberScaffoldState()


    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        AUTH_ROUTE -> bottomBarState.value = false
        MAIN_ROUTE -> bottomBarState.value = true
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            when (bottomBarState.value) {
                true -> BottomNavBar(navController = navController, bottomBarState)
                false -> {}
            }
        },
        backgroundColor = darkBlue,

        ) {

        NavHost(
            navController = navController,
            startDestination = AUTH_ROUTE,
            route = ROOT_ROUTE,
            modifier = Modifier.padding(it)
        )
        {

            authNavGraph(
                navController,
                scaffoldState
            )

//            mainNavGraph(
//                navController = navController,
//                bottomBarState,
//                loginViewModel,
//                workoutViewModel,
//                scaffoldState
//            )

        }
    }

}