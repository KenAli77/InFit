package ken.projects.infit.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import ken.projects.infit.R

const val ROOT_ROUTE = "root_route"
const val LOGIN_ROUTE = "login_route"
const val MAIN_ROUTE = "main_route"

sealed class Screens(
    val route: String,
    @StringRes
    val title: Int, val icon: ImageVector? = null
) {


    object Home : Screens(route = "home_screen", title = R.string.home, icon = Icons.Rounded.Home)
    object Login : Screens(route = "login_screen", title = R.string.login)
    object Signup : Screens(route = "signup_screen", title = R.string.signup)
    object Workout : Screens(route = "workout_screen", title = R.string.workout)
    object Stats :
        Screens(route = "stats_screen", title = R.string.stats, icon = Icons.Rounded.Analytics)

    object WorkoutDetails :
        Screens(route = "workout_details_screen", title = R.string.workout_details)

    object StatsDetails : Screens(route = "stats_details_screen", title = R.string.stats_details)
    object Profile :
        Screens(route = "profile_screen", title = R.string.profile, icon = Icons.Rounded.Person)

    object Exercises : Screens(
        route = "exercises_screen",
        title = R.string.exercises,
        icon = Icons.Rounded.FitnessCenter
    )

    object ExerciseDetails : Screens(route = "exercises_details_screen", title = R.string.exercise)
    object WorkoutPlanSetUp :
        Screens(route = "workout_plan_setup_screen", title = R.string.set_up_workout_plan_heading)


}
