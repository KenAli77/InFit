package ken.projects.infit.features.workout_plan.data.enums

import androidx.annotation.StringRes
import ken.projects.infit.R

enum class Exercise(
    @StringRes
    val label: Int,
    val targetGroup:List<Muscle> = listOf()
) {
    Squat(label = R.string.squat, targetGroup = listOf(Muscle.Hamstrings,Muscle.Quads,Muscle.Calves)),
    BenchPress(label = R.string.bench_press, targetGroup = listOf(Muscle.Chest,Muscle.Triceps,Muscle.AnteriorDeltoids))
}