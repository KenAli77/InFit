package ken.projects.infit.features.workout_plan.data.remote.request

import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import ken.projects.infit.features.workout_plan.data.models.Workout
import java.time.DayOfWeek

data class CreateWorkoutPlanRequest(
    val name:String,
    val frequency:List<DayOfWeek>,
    val difficulty: Difficulty,
    val goal:Goal,
    val duration:Long,
    val workouts:List<Workout>
) : java.io.Serializable
