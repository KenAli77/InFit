package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.dialog

sealed class WorkoutPlanDialogEvent() {

    data class ExerciseDialogEvent(val visible:Boolean):WorkoutPlanDialogEvent()

}
