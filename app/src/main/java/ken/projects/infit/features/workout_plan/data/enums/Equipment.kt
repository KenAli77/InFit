package ken.projects.infit.features.workout_plan.data.enums

import androidx.annotation.StringRes
import ken.projects.infit.R

enum class Equipment(@StringRes val label:Int) {
    Dumbbells(label = R.string.dumbbells),
    Barbell(label = R.string.barbell),
    KettleBell(label = R.string.kettleBell),
    BodyWeight(label = R.string.bodyWeight),
    ResistanceBands(label = R.string.resistanceBands),
    Machine (label = R.string.machine),
    SmithMachine (label = R.string.smithMachine),
    Rope (label = R.string.rope),
    Cable(label = R.string.cable),

}