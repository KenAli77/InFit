package ken.projects.infit.features.workout_plan.data.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ken.projects.infit.R

enum class Difficulty(
    @StringRes
    val difficulty: Int? = null,
    @DrawableRes
    val icon: Int? = null
) {
    Beginner(R.string.beginner, R.drawable.ic_skill_level_basic_icon),
    Intermediate(R.string.intermediate, R.drawable.ic_skill_level_intermediate_icon),
    Advanced(R.string.advanced, R.drawable.ic_skill_level_advanced_icon)
}