package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager

sealed class WorkoutPlanPagerEvent {

    object NavigateBack:WorkoutPlanPagerEvent()
    object NavigateForward:WorkoutPlanPagerEvent()

}