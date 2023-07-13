package ken.projects.infit.features.workout_plan.data.repositories

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import ken.projects.infit.data.models.Workout
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import ken.projects.infit.util.Resource
import javax.inject.Inject

class WorkoutPlanRepositoryImpl @Inject constructor(private val firebase:Firebase):WorkoutPlanRepository {

    override suspend fun getWorkoutPlan(uid: String): Resource<QuerySnapshot> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkoutPlan(workoutPlanId: String, uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editWorkoutPlan(workoutPlanId: String, uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createWorkoutPlan(workoutPlanId: String, uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addWorkoutToPlan(workoutPlanId: String, workout: Workout) {
        TODO("Not yet implemented")
    }
}