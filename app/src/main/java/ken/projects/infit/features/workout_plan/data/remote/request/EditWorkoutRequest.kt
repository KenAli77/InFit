package ken.projects.infit.features.workout_plan.data.remote.request

data class EditWorkoutRequest(
    val id:String,
    val name:String,
    val frequency:String,
    val goal:String,
    val duration:Long

)
