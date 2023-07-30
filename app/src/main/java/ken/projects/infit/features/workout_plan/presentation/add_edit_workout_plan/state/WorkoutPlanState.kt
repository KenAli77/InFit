package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state

import ExerciseItem
import androidx.annotation.StringRes
import ken.projects.infit.R
import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Equipment
import ken.projects.infit.features.workout_plan.data.enums.Exercise
import ken.projects.infit.features.workout_plan.data.enums.Goal
import java.time.DayOfWeek

data class WorkoutPlanState(
    val name: String = "",
    val frequency: List<DayOfWeek> = listOf(),
    val difficulty: Difficulty = Difficulty.Beginner,
    val goal: Goal = Goal.Mass,
    val duration: Int = 0,
    val openExerciseDialog: Boolean = false,
    val exerciseMenuExpanded: Boolean = false,
    val equipmentMenuExpanded: Boolean = false,
    val exercises: List<Exercise> = Exercise.values().toList(),
    val exercise: Exercise = Exercise.Squat,
    val equipments:List<Equipment> = Equipment.values().toList(),
    val equipment: Equipment = Equipment.Barbell,
    val setsTotal: Int = 3,
    @StringRes
    val pagerNavText: Int = R.string.next,
    val pagerBackNavVisible: Boolean = false,
    val pagerPageCount:Int = 3,
    val exerciseItems:List<ExerciseItem> = listOf()
)
