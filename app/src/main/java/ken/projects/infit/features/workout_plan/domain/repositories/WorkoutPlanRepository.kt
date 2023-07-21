package ken.projects.infit.features.workout_plan.domain.repositories

import com.google.firebase.firestore.QuerySnapshot
import ken.projects.infit.data.models.Workout
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan

interface WorkoutPlanRepository {

    suspend fun getWorkoutPlan (workoutPlanId:String): Resource<WorkoutPlan>
    suspend fun deleteWorkoutPlan (workoutPlanId: String):Resource<Unit>
    suspend fun editWorkoutPlan (workoutPlanId: String):Resource<Unit>
    suspend fun createWorkoutPlan (workoutPlan:WorkoutPlan):Resource<Unit>

}