package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.features.workout_plan.data.remote.request.CreateWorkoutRequest
import ken.projects.infit.util.DifficultyLevels
import java.time.DayOfWeek


data class WorkoutPlan (
    val name:String? = null,
    val frequency:List<DayOfWeek>? = null,
    val difficulty: DifficultyLevels.Difficulty?  = null,
    val goal:String?=null
    val duration:Long? = null,
) {
    fun toWorkoutRequest():CreateWorkoutRequest?{
        if(name == null || frequency == null || difficulty == null || goal == null || duration == null){
            return null
        }
        return CreateWorkoutRequest(
            name = name,
            frequency = frequency,
            difficulty = difficulty,
            goal = goal,
            duration = duration
        )
    }
}
