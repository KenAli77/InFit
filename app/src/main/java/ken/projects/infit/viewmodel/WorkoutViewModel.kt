package ken.projects.infit.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.data.models.*
import ken.projects.infit.data.models.states.WorkoutPlanState
import ken.projects.infit.domain.WorkoutRepository
import ken.projects.infit.util.*
import ken.projects.infit.util.DifficultyLevels.Companion.Beginner
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val app: Application,
    private val repository: WorkoutRepository
) : ViewModel() {

    var user: User? by mutableStateOf(User())
        private set

    var userId:String by mutableStateOf("")
    private set

    var workoutPlanState by mutableStateOf(WorkoutPlanState())
        private set

    var workoutId by mutableStateOf("")
        private set

    var workoutDay by mutableStateOf("Today's session")
        private set

    var workoutState by mutableStateOf(Workout())
        private set

    var calendarSelection: LocalDateTime by mutableStateOf(LocalDateTime.now())
        private set

    var muscleGroup = mutableStateListOf<Muscle>()
        private set
    var selectedMuscleGroup by mutableStateOf("")
        private set

    var filteredExerciseList by mutableStateOf(exercises())
        private set

    var userExercisesList by mutableStateOf(exercises())
        private set

    var currentSetItemIndex by mutableStateOf(0)
        private set

    var ongoingWorkout by mutableStateOf(Workout())
        private set

    var isWorkoutStarted by mutableStateOf(false)

    var timeElapsed by mutableStateOf(0.0)

    var timerText by mutableStateOf(getTimeStringFromDouble(timeElapsed))

    var selectedSetItem by mutableStateOf(ExerciseVolume())

    var volumeIndex by mutableStateOf(0)

    var selectedDifficulty by mutableStateOf(Beginner)
        private set

    var selectedDays = mutableStateListOf<DayOfWeek>()
        private set

    var currentExerciseId by mutableStateOf("")

    var statsExercises = mutableStateListOf<String>()
        private set

    var chartData = mutableStateListOf<Double>()
    var historyData = mutableStateListOf<ExerciseHistoryItem>()

    var currentExercise by
    if (isWorkoutStarted) mutableStateOf(ongoingWorkout.exerciseItems?.get(0)) else mutableStateOf(
        workoutState.exerciseItems?.get(
            0
        )
    )
        private set

    fun addDay(day: DayOfWeek) {
        selectedDays.add(day)
    }

    fun removeDay(day: DayOfWeek) {
        selectedDays.remove(day)
    }

    fun startWorkout() {
        ongoingWorkout = workoutState.copy()

        isWorkoutStarted = true
    }

    fun stopWorkout() {
        isWorkoutStarted = false

        ongoingWorkout.exerciseItems?.forEach {
            val historyItem = ExerciseHistoryItem(
                exercise = it.exercise,
                exerciseVolume = it.volume,
            )

            viewModelScope.launch {
                repository.addExerciseHistory(historyItem,userId)
            }
        }
    }

    fun addSetItem() {
        val newItem = currentExercise?.volume?.plus(ExerciseVolume())
        currentExercise = currentExercise?.copy(
            volume = newItem as ArrayList<ExerciseVolume>?
        )
        ongoingWorkout.exerciseItems?.set(currentSetItemIndex, currentExercise!!)
    }

    fun editSetItem(weight: Double, reps: Int) {

        ongoingWorkout.exerciseItems?.let {
            it[currentSetItemIndex].volume?.set(
                volumeIndex, ExerciseVolume(
                    weight = weight,
                    reps = reps
                )
            )
        }

    }

    @Suppress("UNCHECKED_CAST")
    fun removeSetItem(index: Int) {

        val element = currentExercise?.volume?.get(index)

        val newItem = currentExercise?.volume?.minus(element)
        currentExercise = currentExercise?.copy(
            volume = newItem as ArrayList<ExerciseVolume>?
        )
        ongoingWorkout.exerciseItems?.set(currentSetItemIndex, currentExercise!!)

    }

    fun getFirstExercise() {
        val workout = if (isWorkoutStarted) ongoingWorkout else workoutState
        workout.exerciseItems?.let {
            if (it.size > 0)
                currentExercise = it[0]
        }
    }

    fun getNextExercise() {
        val workout = if (isWorkoutStarted) ongoingWorkout else workoutState
        workout.exerciseItems?.let {
            if (currentSetItemIndex < it.size - 1) {
                currentSetItemIndex++
                currentExercise = it[currentSetItemIndex]
            }
        }

    }

    fun getPreviousExercise() {
        val workout = if (isWorkoutStarted) ongoingWorkout else workoutState
        workout.exerciseItems?.let {
            if (currentSetItemIndex > 0) {
                currentSetItemIndex--
                currentExercise = it[currentSetItemIndex]
            }
        }
    }

    fun selectDifficulty(difficulty: DifficultyLevels.Difficulty) {
        selectedDifficulty = difficulty
    }

    fun selectMuscleGroup(targetGroup: List<Muscle>, name: String) {

        selectedMuscleGroup = name

        muscleGroup = targetGroup.toMutableStateList()

    }


    fun selectDay(day: LocalDateTime) {
        calendarSelection = day
    }

    fun getUser(uid:String) {
        userId = uid
        viewModelScope.launch {
            val userData = repository.getUser(uid)
            userData.data?.let {
                user = it.toObject<User>()

            }
        }
    }

    // HISTORY DATA
    fun getHistoryData() {
        viewModelScope.launch {

            val result = repository.getHistoryData(userId)
            val exercises = ArrayList<String>()
            result.data?.let {
                it.documents.forEach {
                    exercises.add(it.id)
                    Log.e("history", it.id)
                }
                statsExercises = exercises.toMutableStateList()

            }
        }
    }

    fun getHistoryDataDetails() {

        viewModelScope.launch {
            val result = repository.getHistoryDataDetails(currentExerciseId,userId)

            result.data?.let {
                val dataList = ArrayList<Double>()
                val historyItems = ArrayList<ExerciseHistoryItem>()
                it.documents.forEach { doc ->

                    val docData = doc.toObject<ExerciseHistoryItem>()

                    docData?.let { item ->
                        historyItems.add(item)

                    }
                }

                historyItems.sortedBy { it.date }

                historyData = historyItems.toMutableStateList()

                historyItems.forEach {

                    dataList.add(it.maxWeight!!)

                }

                chartData = dataList.toMutableStateList()
            }
        }
    }

    fun getDayString(day: LocalDateTime) {
        val formatter = DateTimeFormatter.ofPattern("EEE, d MMM", Locale.ENGLISH)
        val stringDate = day.format(formatter)

        val date = if (day.dayOfYear == LocalDateTime.now().dayOfYear) {
            "Today's session"
        } else {
            stringDate

        }

        workoutDay = date
    }

    // WORKOUT DATA
    fun getWorkoutPlan() =
        viewModelScope.launch {

            when (val result = repository.getWorkoutPlan(userId)) {

                is Resource.Success -> {
                    result.data?.let {
                        val data = it.documents.firstOrNull()?.toObject<WorkoutPlan>()
                        workoutPlanState = workoutPlanState.copy(
                            workoutPlan = data,
                            loading = false
                        )

                        Log.e("Workout", workoutPlanState.workoutPlan?.name.toString())
                    } ?: kotlin.run {
                        workoutPlanState = workoutPlanState.copy(
                            workoutPlan = null,
                            loading = false
                        )
                    }

                }
                is Resource.Loading -> {
                    workoutPlanState = workoutPlanState.copy(
                        workoutPlan = null,
                        loading = true
                    )
                }
                is Resource.Error -> {
                    workoutPlanState = workoutPlanState.copy(
                        loading = false,
                        error = result.message.toString()
                    )
                }

            }

        }

    fun getWorkouts() = viewModelScope.launch {

        when (val result = repository.getWorkouts(userId)) {

            is Resource.Success -> {
                result.data?.let {

                    val workouts = ArrayList<Workout>()

                    it.documents.forEach {
                        it.toObject<Workout>()?.let { it1 ->
                            workouts.add(it1)
                        }
                    }
                    val selectedWorkout =
                        workouts.first { it.dayOfWeek == calendarSelection.dayOfWeek }

                    workoutState = selectedWorkout
                    workoutId = selectedWorkout.dayOfWeek.toString()

                }
            }

            is Resource.Loading -> {}

            is Resource.Error -> {}
        }

    }

    fun updateWorkout() = viewModelScope.launch {

        repository.updateWorkout(workout = ongoingWorkout, workoutId = workoutId, uid = userId)
    }

    fun addWorkoutPlan(
        workoutPlan: WorkoutPlan = DefaultWorkoutPlans.TotalBody.workoutPlan,
    ) =
        viewModelScope.launch {

            val workoutsList = ArrayList<Workout>()

            workoutPlan.workouts?.forEach {
                workoutsList.add(
                    Workout(
                        dayOfWeek = it,
                        duration = workoutPlan.duration
                    )
                )
            }

            repository.addWorkoutPlan(workoutPlan, workoutsList,userId)
        }

    // EXERCISE DATA
    fun getExercises() = viewModelScope.launch {

        val muscleGroup = muscleGroup.toList()

        val filterMuscleGroups = Muscle.values().intersect(muscleGroup.toSet()).toList()

        repository.getExercises(userId).observeForever {

            it.data?.let {

                val newExercises = mutableListOf<Exercise>()

                it.forEach { doc ->
                    val exercise = doc.toObject<Exercise>()
                    newExercises.add(exercise)
                }

                val allExercises = exercises().plus(newExercises).toSet().toList()

                val filteredExercises =
                    exercises().plus(newExercises)
                        .filter { it.targetMuscles!!.containsAll(filterMuscleGroups) }
                        .toSet()
                        .toList()

                userExercisesList = allExercises
                filteredExerciseList = filteredExercises

            }
        }


    }

    fun addExerciseToWorkout(exerciseName: String, equipments: Equipment, sets: Int) =

        viewModelScope.launch {

            val exerciseItems: ArrayList<ExerciseItem> = ArrayList<ExerciseItem>()

            val volume: ArrayList<ExerciseVolume> = ArrayList()

            for (i in 1..sets) {
                volume.add(ExerciseVolume(set = i))
            }

            val exerciseItem = ExerciseItem(
                exercise = userExercisesList.first { it.name == exerciseName },
                name = exerciseName,
                equipments = equipments,
                sets = sets,
                volume = volume
            )

            workoutState.exerciseItems?.let {
                exerciseItems.addAll(it)

            }

            exerciseItems.add(exerciseItem)

            val workout = Workout(
                name = workoutState.name,
                targetMuscleGroups = workoutState.targetMuscleGroups,
                duration = workoutState.duration,
                dayOfWeek = workoutState.dayOfWeek,
                exerciseItems = exerciseItems
            )

            repository.updateWorkout(workout = workout, workoutId = workoutId, uid = userId)


        }

    fun removeExercise(exerciseItem: ExerciseItem) = viewModelScope.launch {

        val exercises = workoutState.exerciseItems?.minus(exerciseItem)

        val workout = Workout(
            name = workoutState.name,
            targetMuscleGroups = workoutState.targetMuscleGroups,
            duration = workoutState.duration,
            dayOfWeek = workoutState.dayOfWeek,
            exerciseItems = exercises as ArrayList<ExerciseItem>?
        )

        repository.updateWorkout(workoutId = workoutId, workout = workout, uid = userId)


    }

    fun addNewExercise(exercise: Exercise) = viewModelScope.launch {

        repository.addNewExercise(exercise,userId)

    }

    // DELETE DATA
    fun deleteWorkoutPlan() {
        viewModelScope.launch {

            repository.deleteWorkoutPlan(workoutPlanState.workoutPlan?.name!!,userId)

            workoutPlanState = workoutPlanState.copy(
                workoutPlan = null
            )

        }
    }

}


