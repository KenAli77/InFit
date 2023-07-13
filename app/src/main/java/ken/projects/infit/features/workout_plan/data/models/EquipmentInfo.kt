package ken.projects.infit.features.workout_plan.data.models

import androidx.annotation.StringRes
import ken.projects.infit.features.workout_plan.data.enums.Equipment

data class EquipmentInfo(
    @StringRes
    val name: Int? = null,
    val equipment: Equipment? = null
)
