package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state

data class WorkoutPlanState(
    val name:String?=null,
    val frequency:String?=null,
    val goal:String?=null,
    val duration:Long?=null,
)
