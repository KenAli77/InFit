package ken.projects.infit.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import ken.projects.infit.data.models.*
import ken.projects.infit.util.Resource

interface WorkoutRepository {


    suspend fun addWorkoutPlan(
        workoutPlan: WorkoutPlan,
        workouts: ArrayList<Workout>,
        uid:String
    ): Resource<Void>

    suspend fun getWorkoutPlan(uid:String): Resource<QuerySnapshot>

    suspend fun addWorkouts(uid:String): Resource<Void>

    suspend fun getWorkouts(uid:String): Resource<QuerySnapshot>

    suspend fun getExercises(uid:String):MutableLiveData<Resource<QuerySnapshot>>

    suspend fun getUser(uid:String): Resource<DocumentSnapshot>

    suspend fun addExerciseToWorkout(exerciseItem: ExerciseItem, workoutId:String,uid:String): Resource<Void>
    suspend fun updateWorkout(workoutId: String,
                              exerciseItem: ExerciseItem? = null,
                              volume: ExerciseVolume? = null,
                              workout: Workout,
                              uid:String
    ): Resource<Void>

    suspend fun addNewExercise(exercise: Exercise,uid:String):Resource<Void>

    suspend fun addExerciseHistory(exercise:ExerciseHistoryItem,uid:String)

    suspend fun getHistoryData(uid:String):Resource<QuerySnapshot>

    suspend fun getHistoryDataDetails(exerciseId: String,uid:String): Resource<QuerySnapshot>
    suspend fun deleteWorkoutPlan(workoutPlanId: String,uid:String)




}