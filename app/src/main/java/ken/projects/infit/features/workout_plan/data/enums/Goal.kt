package ken.projects.infit.features.workout_plan.data.enums

import androidx.annotation.StringRes
import ken.projects.infit.R

enum class Goal(@StringRes val description:Int, @StringRes val title:Int) {

    Mass(description = R.string.description_mass,title = R.string.title_mass),
    FatLoss(description = R.string.description_fat_loss,title = R.string.title_fat_loss),
    Maintain(description = R.string.description_maintain,title = R.string.title_maintain),

}