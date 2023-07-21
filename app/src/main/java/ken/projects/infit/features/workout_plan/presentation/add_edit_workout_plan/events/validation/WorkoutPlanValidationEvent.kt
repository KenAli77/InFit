package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.validation

sealed class WorkoutPlanValidationEvent {

    object Success: WorkoutPlanValidationEvent()
    object Failure: WorkoutPlanValidationEvent()
}
