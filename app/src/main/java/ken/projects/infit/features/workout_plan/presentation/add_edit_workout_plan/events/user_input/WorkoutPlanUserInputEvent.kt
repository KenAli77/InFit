package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input

import ken.projects.infit.util.DifficultyLevels

sealed class WorkoutPlanUserInputEvent{

    data class EnteredWorkoutName(val name:String,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutFrequency(val frequency:List<String>,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutDifficulty(val difficulty: DifficultyLevels.Difficulty,):
        WorkoutPlanUserInputEvent()
    // TODO create class for workout focus (MASS,HEALTH,FAT LOSS)
    data class EnteredWorkoutGoal(val goal:String,): WorkoutPlanUserInputEvent()
    data class EnteredWorkoutDuration(val duration:Long,): WorkoutPlanUserInputEvent()
}
