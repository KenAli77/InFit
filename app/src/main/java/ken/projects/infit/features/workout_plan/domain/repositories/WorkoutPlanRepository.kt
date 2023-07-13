package ken.projects.infit.features.workout_plan.domain.repositories

import com.google.firebase.firestore.QuerySnapshot
import ken.projects.infit.data.models.Workout
import ken.projects.infit.util.Resource

interface WorkoutPlanRepository {

    suspend fun getWorkoutPlan (uid:String): Resource<QuerySnapshot>
    suspend fun deleteWorkoutPlan (workoutPlanId: String,uid:String)
    suspend fun editWorkoutPlan (workoutPlanId: String,uid:String)

    suspend fun addWorkoutToPlan(workoutPlanId: String,workout:Workout)

}