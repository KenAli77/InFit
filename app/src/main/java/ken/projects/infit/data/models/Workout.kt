package ken.projects.infit.data.models


import java.time.DayOfWeek

data class Workout(
    val name: String? = null,
    val targetMuscleGroups: ArrayList<String>? = null,
    val duration: Int? = null,
    val dayOfWeek: DayOfWeek? = null,
    val exerciseItems: ArrayList<ExerciseItem>? = null,
)
