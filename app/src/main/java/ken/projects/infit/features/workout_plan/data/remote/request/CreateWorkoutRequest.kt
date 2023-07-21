package ken.projects.infit.features.workout_plan.data.remote.request

import ken.projects.infit.util.DifficultyLevels
import java.time.DayOfWeek

data class CreateWorkoutRequest(
    val name:String,
    val frequency:List<DayOfWeek>,
    val difficulty:DifficultyLevels.Difficulty,
    val goal:String,
    val duration:Long

)
