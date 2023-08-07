package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.utils

import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state.WorkoutPlanState

fun WorkoutPlanState.toWorkoutPlan(): WorkoutPlan{

    return WorkoutPlan(
        name = name,
        frequency = frequency,
        difficulty = difficulty,
        goal = goal,
        duration = duration.toLong(),
        workouts = listOf()
    )

}