package ken.projects.infit.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.veryDarkBlue
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.components.WorkoutPlanScreen
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel.WorkoutPlanViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    navigation(startDestination = Screens.WorkoutPlanSetUp.route, route = MAIN_ROUTE)
    {
//        composable(
//            route = Screens.Dashboard.route
//        ) {
//
//            HomeScreen(navController, loginViewModel, workoutViewModel, scaffoldState)
//            bottomBarState.value = true
//        }
//
//        composable(
//            route = Screens.Stats.route
//        ) {
//            StatsScreen(navController, workoutViewModel)
//            bottomBarState.value = true
//        }
//
//        composable(
//            route = Screens.Profile.route
//        ) {
//            /*
//            TODO: PROFILE SCREEN
//             */
//            ProfileScreen()
//            bottomBarState.value = true
//        }
//
//        composable(
//            route = Screens.StatsDetails.route
//        ) {
//            StatsDetailScreen(navController, workoutViewModel)
//            bottomBarState.value = true
//        }
//
//        composable(
//            route = Screens.WorkoutDetails.route
//        ) {
//            WorkoutDetailScreen(navController, workoutViewModel)
//            bottomBarState.value = true
//        }
//
//        composable(
//            route = Screens.Workout.route
//        ) {
//            WorkoutScreen(workoutViewModel, navController)
//            bottomBarState.value = true
//        }
//
//        composable(route = Screens.Exercise.route) {
//            ExercisesScreen(navController, workoutViewModel)
//            bottomBarState.value = true
//        }

//        composable(route = Screens.ExerciseDetails.route) {
//            ExerciseDetailScreen(Modifier, navController, workoutViewModel)
//            bottomBarState.value = true
//        }

        composable(route = Screens.WorkoutPlanSetUp.route) {
            val viewModel = hiltViewModel<WorkoutPlanViewModel>(it)
            WorkoutPlanScreen(viewmodel = viewModel,navController,scaffoldState = scaffoldState)
        }



    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController = rememberNavController(),
    bottomBarState: MutableState<Boolean>
) {
    val screens = listOf(
        Screens.Dashboard,
        Screens.Stats,
        Screens.Profile,
        Screens.Exercises
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = bottomBarState.value, enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            modifier = Modifier
                .graphicsLayer {
                    clip = true
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                }
                .height(70.dp),
            elevation = 10.dp,
            backgroundColor = holoGreen,


            ) {

            screens.forEach {

                this@BottomNavigation.AddItem(
                    screens = it,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }


        }
    }

}

@Composable
fun RowScope.AddItem(
    screens: Screens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    BottomNavigationItem(
        label = {
            Text(text = stringResource(id = screens.title))
        },

        onClick = {
            navController.navigate(screens.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = { Icon(imageVector = screens.icon!!, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
        selectedContentColor = veryDarkBlue,
        unselectedContentColor = veryDarkBlue.copy(ContentAlpha.disabled),


        )
}