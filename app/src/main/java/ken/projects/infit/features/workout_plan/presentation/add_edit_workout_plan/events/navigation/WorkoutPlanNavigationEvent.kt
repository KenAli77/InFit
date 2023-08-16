package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.navigation

sealed class WorkoutPlanNavigationEvent {

    data class Navigate(val route:String): WorkoutPlanNavigationEvent()
}
