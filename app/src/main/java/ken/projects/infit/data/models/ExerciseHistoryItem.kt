package ken.projects.infit.data.models


data class ExerciseHistoryItem(
    val exercise: Exercise? = null,
    val date: Long? = System.currentTimeMillis(),
    val exerciseVolume: List<ExerciseVolume>? = null,
    val maxWeight: Double? = exerciseVolume?.let {
        it.maxOf {
            it.weight!!
        }
    },
    val minWeight: Double? = exerciseVolume?.let {
        it.minOf {
            it.weight!!
        }
    },
    val totalSets: Int? = exerciseVolume?.count(),
    val totalReps: Int? = exerciseVolume?.let {
        it.sumOf { it.reps!! }
    }
)