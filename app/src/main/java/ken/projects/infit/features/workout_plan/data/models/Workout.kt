package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.features.workout_plan.data.enums.Muscle
import java.time.DayOfWeek

data class Workout(
    val id:String?=null,
    val name: String,
    val targetMuscleGroups: List<Muscle>,
    val duration: Int,
    val dayOfWeek: DayOfWeek,
    val exerciseItems: List<ExerciseItem>,
): java.io.Serializable
