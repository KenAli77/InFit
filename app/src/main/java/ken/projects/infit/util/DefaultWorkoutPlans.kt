package ken.projects.infit.util

import ken.projects.infit.data.models.*
import ken.projects.infit.util.DifficultyLevels.Companion.Beginner
import java.time.DayOfWeek

sealed class DefaultWorkoutPlans(val workoutPlan: WorkoutPlan) {

    object TotalBody : DefaultWorkoutPlans(
        workoutPlan = WorkoutPlan(
            name = "Total Body",
            workouts = arrayListOf(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
            ),
            difficulty = Beginner,
            duration = 45
        )
    )

}

val defaultWorkouts = generateWorkouts()

fun generateWorkouts(): ArrayList<Workout> {

    val defaultWorkouts = ArrayList<Workout>()
    val volume = ArrayList<ExerciseVolume>()

    for (i in 1..4) {
        volume.add(ExerciseVolume(set = i, reps = 15))
    }

    val days = listOf<DayOfWeek>(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY
    )

    val exerciseItems = arrayListOf(
        ExerciseItem(
            name = "squat",
            targetMuscles = arrayListOf(Muscle.Quads, Muscle.Hamstrings),
            equipments = equipments[0],
            volume = volume
        ),
        ExerciseItem(
            name = "chest press",
            targetMuscles = arrayListOf(Muscle.Quads, Muscle.Hamstrings),
           equipments = equipments[0],
            volume = volume
        ),
        ExerciseItem(
            name = "dips",
            targetMuscles = arrayListOf(Muscle.Quads, Muscle.Hamstrings),
            equipments = equipments[0],
            volume = volume
        ),
        ExerciseItem(
            name = "rows",
            targetMuscles = arrayListOf(Muscle.Quads, Muscle.Hamstrings),
            equipments = equipments[0],
            volume = volume
        )


    )

    for (day in days) {

        defaultWorkouts.add(
            Workout(
                name = day.name,
                dayOfWeek = day,
                exerciseItems = exerciseItems
            )
        )

    }

    return defaultWorkouts


}