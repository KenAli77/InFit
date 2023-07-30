package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click

import ExerciseItem

sealed class WorkoutPlanClickEvent {

    object FinishButtonClickEvent : WorkoutPlanClickEvent()
    object AddExerciseButtonClickEvent : WorkoutPlanClickEvent()
    object SaveExerciseClickEvent : WorkoutPlanClickEvent()
    data class RemoveExerciseClickEvent(val item:ExerciseItem,val index:Int) : WorkoutPlanClickEvent()
}