package ken.projects.infit.features.workout_plan.data.remote.response

import com.google.gson.annotations.SerializedName
import ken.projects.infit.features.workout_plan.data.enums.Muscle
import ken.projects.infit.features.workout_plan.data.models.ExerciseItem
import java.time.DayOfWeek

data class WorkoutResponse(
    @SerializedName("_id")
    val id: String? = null,
    val dayOfWeek: DayOfWeek? = null,
    val duration: Int? = null,
    val exerciseItems: List<ExerciseItem>? = null,
    val name: String? = null,
    val targetMuscleGroups: List<Muscle>? = null,
    @SerializedName("workoutPlan")
    val workoutPlanId: String? = null
) : java.io.Serializable