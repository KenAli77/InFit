import ken.projects.infit.features.workout_plan.data.enums.Equipment
import ken.projects.infit.features.workout_plan.data.enums.Exercise
import ken.projects.infit.features.workout_plan.data.models.ExerciseVolume

data class ExerciseItem(
    val exercise: Exercise? = null,
    val equipment: Equipment? = null,
    val volume: ArrayList<ExerciseVolume>? = null,
    val sets: Int? = volume?.count()
)



//val exercises = ArrayList<Exercise>()

//fun exercises(): List<Exercise> {
//
//    val squat = Exercise("Squat", Equipment.Barbell, arrayListOf(Muscle.Quads, Muscle.Hamstrings))
//    val benchPress =
//        Exercise("Bench Press", Equipment.Barbell, arrayListOf(Muscle.Chest, Muscle.Triceps))
//    val deadlift =
//        Exercise("Deadlift", Equipment.Barbell, arrayListOf(Muscle.Hamstrings, Muscle.LowerBack))
//    val shrugs = Exercise("Shrugs", Equipment.Dumbbells, arrayListOf(Muscle.Traps))
//    val bicepCurl = Exercise("Bicep Curl", Equipment.Barbell, arrayListOf(Muscle.Biceps))
//    val tricepsExtension =
//        Exercise("Triceps Extension", Equipment.Rope, arrayListOf(Muscle.Triceps))
//    val narrowGripPress =
//        Exercise("Narrow Grip Press", Equipment.Barbell, arrayListOf(Muscle.Triceps))
//    val lateralRaise =
//        Exercise("Lateral Raise", Equipment.Dumbbells, arrayListOf(Muscle.LateralDeltoids))
//    val legCurl = Exercise("Leg Curl", Equipment.Machine, arrayListOf(Muscle.Hamstrings))
//    val calveRaise = Exercise("Calf Raise", Equipment.Machine, arrayListOf(Muscle.Calves))
//    val legPress = Exercise("Leg Press", Equipment.Machine, arrayListOf(Muscle.Quads))
//    val overheadPress = Exercise(
//        "Overhead Press",
//        Equipment.Barbell,
//        arrayListOf(Muscle.LateralDeltoids, Muscle.AnteriorDeltoids)
//    )
//
//    exercises.addAll(
//        arrayListOf(
//            squat,
//            benchPress,
//            deadlift,
//            shrugs,
//            bicepCurl,
//            tricepsExtension,
//            narrowGripPress,
//            lateralRaise,
//            legCurl,
//            calveRaise,
//            legPress,
//            overheadPress
//
//        )
//    )
//
//    return exercises
//}

//val equipments = ArrayList<EquipmentInfo>()

//fun equipments(): List<EquipmentInfo> {
//
//    val dumbbells = EquipmentInfo(name = R.string.dumbbells, equipment = Equipment.Dumbbells)
//    val barbell = EquipmentInfo(name = R.string.barbell, equipment = Equipment.Barbell)
//    val kettleBell = EquipmentInfo(name = R.string.kettleBell, equipment = Equipment.KettleBell)
//    val bodyWeight = EquipmentInfo(name = R.string.bodyWeight, equipment = Equipment.BodyWeight)
//    val resistanceBands =
//        EquipmentInfo(name = R.string.resistanceBands, equipment = Equipment.ResistanceBands)
//    val machine = EquipmentInfo(name = R.string.machine, equipment = Equipment.Machine)
//    val smithMachine = EquipmentInfo(name = R.string.smithMachine, equipment = Equipment.SmithMachine)
//    val rope = EquipmentInfo(name = R.string.rope, equipment = Equipment.Rope)
//    val cable = EquipmentInfo(name = R.string.cable, equipment= Equipment.Cable)
//
//    equipments.addAll(
//        arrayListOf(
//            dumbbells,
//            barbell,
//            kettleBell,
//            bodyWeight,
//            resistanceBands,
//            machine,
//            smithMachine,
//            rope,
//            cable
//            )
//    )
//
//    return equipments
//}







