package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input

import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Equipment
import ken.projects.infit.features.workout_plan.data.enums.Exercise
import ken.projects.infit.features.workout_plan.data.enums.Goal
import java.time.DayOfWeek

sealed class WorkoutPlanUserInputEvent{

    data class EnteredWorkoutName(val name:String,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutFrequency(val day: DayOfWeek, val checked:Boolean,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutDifficulty(val difficulty: Difficulty,):
        WorkoutPlanUserInputEvent()
    data class EnteredWorkoutGoal(val goal:Goal,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutDuration(val duration:Int,): WorkoutPlanUserInputEvent()
    data class SelectedEquipment(val equipment: Equipment):WorkoutPlanUserInputEvent()
    data class SelectedExercise(val exercise: Exercise):WorkoutPlanUserInputEvent()
    data class EnteredSetTotal(val sets:Int):WorkoutPlanUserInputEvent()
}
