package ken.projects.infit.data.models

import ken.projects.infit.util.DifficultyLevels
import java.time.DayOfWeek

data class WorkoutPlan (
    val name:String? = null,
    val workouts:ArrayList<DayOfWeek>? = null,
    val difficulty: DifficultyLevels.Difficulty?  = null,
    val duration:Int? = null,
)
