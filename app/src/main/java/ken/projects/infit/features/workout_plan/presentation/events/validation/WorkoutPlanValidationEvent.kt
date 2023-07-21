package ken.projects.infit.features.workout_plan.presentation.events.validation

sealed class WorkoutPlanValidationEvent {

    object Success:WorkoutPlanValidationEvent()
    object Failure:WorkoutPlanValidationEvent()
}
