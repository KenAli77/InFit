package ken.projects.infit.data.models

import androidx.annotation.StringRes
import ken.projects.infit.R

data class ExerciseItem(
    val exercise: Exercise? = null,
    val name: String? = null,
    val targetMuscles: ArrayList<Muscle>? = null,
    val equipments: Equipment? = null,
    val volume: ArrayList<ExerciseVolume>? = null,
    val sets: Int? = volume?.count()
)

data class Exercise(
    val name: String? = null,
    val equipments: Equipments? = null,
    val targetMuscles: List<Muscle>? = null
)

data class Equipment(
    @StringRes
    val name: Int? = null,
    val equipments: Equipments? = null
)


enum class Muscle {
    Biceps,
    Chest,
    Quads,
    Hamstrings,
    Triceps,
    Traps,
    Glutes,
    Calves,
    AnteriorDeltoids,
    PosteriorDeltoids,
    LateralDeltoids,
    Abs,
    Lats,
    Rhomboids,
    LowerBack,
}

enum class Equipments {
    Dumbbells,
    Barbell,
    KettleBell,
    BodyWeight,
    ResistanceBands,
    Machine,
    SmithMachine,
    Rope,
    Cable
}

val exercises = ArrayList<Exercise>()

fun exercises(): List<Exercise> {

    val squat = Exercise("Squat", Equipments.Barbell, arrayListOf(Muscle.Quads, Muscle.Hamstrings))
    val benchPress =
        Exercise("Bench Press", Equipments.Barbell, arrayListOf(Muscle.Chest, Muscle.Triceps))
    val deadlift =
        Exercise("Deadlift", Equipments.Barbell, arrayListOf(Muscle.Hamstrings, Muscle.LowerBack))
    val shrugs = Exercise("Shrugs", Equipments.Dumbbells, arrayListOf(Muscle.Traps))
    val bicepCurl = Exercise("Bicep Curl", Equipments.Barbell, arrayListOf(Muscle.Biceps))
    val tricepsExtension =
        Exercise("Triceps Extension", Equipments.Rope, arrayListOf(Muscle.Triceps))
    val narrowGripPress =
        Exercise("Narrow Grip Press", Equipments.Barbell, arrayListOf(Muscle.Triceps))
    val lateralRaise =
        Exercise("Lateral Raise", Equipments.Dumbbells, arrayListOf(Muscle.LateralDeltoids))
    val legCurl = Exercise("Leg Curl", Equipments.Machine, arrayListOf(Muscle.Hamstrings))
    val calveRaise = Exercise("Calf Raise", Equipments.Machine, arrayListOf(Muscle.Calves))
    val legPress = Exercise("Leg Press", Equipments.Machine, arrayListOf(Muscle.Quads))
    val overheadPress = Exercise(
        "Overhead Press",
        Equipments.Barbell,
        arrayListOf(Muscle.LateralDeltoids, Muscle.AnteriorDeltoids)
    )

    exercises.addAll(
        arrayListOf(
            squat,
            benchPress,
            deadlift,
            shrugs,
            bicepCurl,
            tricepsExtension,
            narrowGripPress,
            lateralRaise,
            legCurl,
            calveRaise,
            legPress,
            overheadPress

        )
    )

    return exercises
}

val equipments = ArrayList<Equipment>()

fun equipments(): List<Equipment> {

    val dumbbells = Equipment(name = R.string.dumbbells, equipments = Equipments.Dumbbells)
    val barbell = Equipment(name = R.string.barbell, equipments = Equipments.Barbell)
    val kettleBell = Equipment(name = R.string.kettleBell, equipments = Equipments.KettleBell)
    val bodyWeight = Equipment(name = R.string.bodyWeight, equipments = Equipments.BodyWeight)
    val resistanceBands =
        Equipment(name = R.string.resistanceBands, equipments = Equipments.ResistanceBands)
    val machine = Equipment(name = R.string.machine, equipments = Equipments.Machine)
    val smithMachine = Equipment(name = R.string.smithMachine, equipments = Equipments.SmithMachine)
    val rope = Equipment(name = R.string.rope, equipments = Equipments.Rope)
    val cable = Equipment(name = R.string.cable, equipments = Equipments.Cable)

    equipments.addAll(
        arrayListOf(
            dumbbells,
            barbell,
            kettleBell,
            bodyWeight,
            resistanceBands,
            machine,
            smithMachine,
            rope,
            cable
            )
    )

    return equipments
}







