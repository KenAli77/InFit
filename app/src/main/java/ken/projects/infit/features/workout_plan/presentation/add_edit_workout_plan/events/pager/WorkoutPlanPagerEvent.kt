package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager

sealed class WorkoutPlanPagerEvent {

    data class NavigateBack(val currentPage:Int,):WorkoutPlanPagerEvent()
    data class NavigateForward(val currentPage:Int,):WorkoutPlanPagerEvent()

}