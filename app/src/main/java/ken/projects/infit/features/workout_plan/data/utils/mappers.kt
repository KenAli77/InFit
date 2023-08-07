package ken.projects.infit.features.workout_plan.data.utils

import ken.projects.infit.features.workout_plan.data.models.Workout
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.data.remote.response.WorkoutPlanResponse
import ken.projects.infit.features.workout_plan.data.remote.response.WorkoutResponse

fun WorkoutPlanResponse.toWorkoutPlan(): WorkoutPlan? {
    return if (
        name != null
        && frequency != null
        && difficulty != null
        && duration != null
        && goal != null
        && workouts != null
        && id != null
    ) {

        val workoutList = ArrayList<Workout>()

        for (workout in workouts){
            val workoutData = workout.toWorkout()
            workoutData?.let {
                workoutList.add(it)
            } ?: return null
        }

        WorkoutPlan(
            id = id,
            name = name,
            frequency = frequency,
            duration = duration.toLong(),
            difficulty = difficulty,
            goal = goal,
            workouts = workoutList
        )
    } else null
}

fun WorkoutResponse.toWorkout() : Workout? {
    return if (
        id != null
        && dayOfWeek != null
        && exerciseItems != null
        && duration != null
        && targetMuscleGroups != null
        && workoutPlanId != null
        && name != null
    ) {
        Workout(
            id = id,
            name = name,
            dayOfWeek = dayOfWeek,
            duration = duration,
            targetMuscleGroups = targetMuscleGroups,
            exerciseItems = exerciseItems,
        )
    } else null
}