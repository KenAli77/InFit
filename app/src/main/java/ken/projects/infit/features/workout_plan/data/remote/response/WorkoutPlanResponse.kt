package ken.projects.infit.features.workout_plan.data.remote.response

import com.google.gson.annotations.SerializedName
import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import java.time.DayOfWeek

data class WorkoutPlanResponse(
    @SerializedName("_id")
    val id: String? = null,
    val difficulty: Difficulty? = null,
    val duration: Long? = null,
    val frequency: List<DayOfWeek>? = null,
    val goal: Goal? = null,
    val name: String? = null,
    val workouts: List<WorkoutResponse>? = null
) : java.io.Serializable