package ken.projects.infit.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ken.projects.infit.R

sealed class DifficultyLevels() {

    data class Difficulty(
        @StringRes
        val difficulty: Int? = null,
        @DrawableRes
        val icon: Int? = null
    )

    companion object {
        val Beginner = Difficulty(R.string.beginner, R.drawable.ic_skill_level_basic_icon)
        val Intermediate = Difficulty(R.string.intermediate, R.drawable.ic_skill_level_intermediate_icon)
        val Advanced = Difficulty(R.string.advanced, R.drawable.ic_skill_level_advanced_icon)

    }


}
