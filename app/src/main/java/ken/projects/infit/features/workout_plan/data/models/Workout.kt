package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.data.models.ExerciseItem
import java.time.DayOfWeek

data class Workout(
    val name: String? = null,
    val targetMuscleGroups: ArrayList<String>? = null,
    val duration: Int? = null,
    val dayOfWeek: DayOfWeek? = null,
    val exerciseItems: ArrayList<ExerciseItem>? = null,
)
