package ken.projects.infit.features.workout_plan.data.remote.response

import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import java.time.DayOfWeek


data class WorkoutPlanResponse(
    val name:String? = null,
    val frequency:ArrayList<DayOfWeek>?=null,
    val difficulty: Difficulty?  = null,
    val duration:Int? = null,
    val goal: Goal? = null
) {
    fun toWorkoutPlan():WorkoutPlan? {
       return if(name != null && frequency != null && difficulty != null && duration != null && goal != null){
           WorkoutPlan(
               name = name,
               frequency = frequency,
               duration = duration.toLong(),
               difficulty = difficulty,
               goal = goal
           )
        } else null
    }
}
