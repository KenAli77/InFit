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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ken.projects.infit.ui.theme.darkBlue
import ken.projects.infit.ui.theme.lightBlue

@Composable
fun RootNavGraph(
    navController: NavHostController,
) {

    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val scaffoldState = rememberScaffoldState()

    val systemUiController = rememberSystemUiController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.parent?.route) {
        AUTH_ROUTE -> {
            systemUiController.setStatusBarColor(
                color = lightBlue,
            )
        }
        MAIN_ROUTE-> {
            systemUiController.setStatusBarColor(
                color = darkBlue,
            )
            systemUiController.setSystemBarsColor(
                color = darkBlue
            )
        }
    }


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

            mainNavGraph(
                navController = navController,
                scaffoldState
            )

        }
    }

}