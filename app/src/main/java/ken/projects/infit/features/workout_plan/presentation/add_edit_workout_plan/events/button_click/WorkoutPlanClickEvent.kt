package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click

sealed class WorkoutPlanClickEvent {

    object FinishButtonClickEvent : WorkoutPlanClickEvent()
    object AddExerciseButtonClickEvent : WorkoutPlanClickEvent()

}