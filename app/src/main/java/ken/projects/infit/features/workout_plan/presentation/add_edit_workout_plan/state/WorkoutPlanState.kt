package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state

import androidx.annotation.StringRes
import ken.projects.infit.R
import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import java.time.DayOfWeek

data class WorkoutPlanState(
    val name:String="",
    val frequency:List<DayOfWeek> = listOf(),
    val difficulty:Difficulty = Difficulty.Beginner,
    val goal: Goal = Goal.Mass,
    val duration:Int=0,
    @StringRes
    val pagerNavText:Int = R.string.next,
    val pagerBackNavVisible:Boolean = false
)
