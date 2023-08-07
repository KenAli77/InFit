package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import ken.projects.infit.features.workout_plan.data.remote.request.CreateWorkoutPlanRequest
import java.time.DayOfWeek

data class WorkoutPlan (
    val id:String?=null,
    val name:String,
    val frequency:List<DayOfWeek>,
    val difficulty:Difficulty,
    val goal:Goal,
    val duration:Long,
    val workouts:List<Workout>
) {
    fun toWorkoutRequest():CreateWorkoutPlanRequest{
        return CreateWorkoutPlanRequest(
            name = name,
            frequency = frequency,
            difficulty = difficulty,
            goal = goal,
            duration = duration,
            workouts = workouts
        )
    }
}
