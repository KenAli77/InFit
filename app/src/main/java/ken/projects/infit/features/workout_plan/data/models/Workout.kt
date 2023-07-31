package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.data.models.ExerciseItem
import ken.projects.infit.features.workout_plan.data.enums.Muscle
import java.time.DayOfWeek

data class Workout(
    val name: String? = null,
    val targetMuscleGroups: List<Muscle>? = null,
    val duration: Int? = null,
    val dayOfWeek: DayOfWeek? = null,
    val exerciseItems: List<ExerciseItem>? = null,
) {
    fun toRequest(){

    }
}
