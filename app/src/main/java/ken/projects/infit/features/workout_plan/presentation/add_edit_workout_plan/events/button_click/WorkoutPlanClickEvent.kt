package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click

sealed class WorkoutPlanClickEvent {

    object NextButtonClickEvent : WorkoutPlanClickEvent()
    data class PagerNavClickEvent(val id:Int,):WorkoutPlanClickEvent()
    object BackButtonClickEvent : WorkoutPlanClickEvent()
    object FinishButtonClickEvent : WorkoutPlanClickEvent()

}