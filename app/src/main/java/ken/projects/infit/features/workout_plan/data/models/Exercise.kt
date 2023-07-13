package ken.projects.infit.features.workout_plan.data.models

import ken.projects.infit.features.workout_plan.data.enums.Equipment
import ken.projects.infit.features.workout_plan.data.enums.Muscle

data class Exercise(
    val name: String? = null,
    val equipment: Equipment? = null,
    val targetMuscles: List<Muscle>? = null
)
