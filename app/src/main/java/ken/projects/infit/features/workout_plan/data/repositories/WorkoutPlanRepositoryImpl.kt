package ken.projects.infit.features.workout_plan.data.repositories

import com.google.firebase.firestore.QuerySnapshot
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import ken.projects.infit.util.Resource

class WorkoutPlanRepositoryImpl:WorkoutPlanRepository {

    override suspend fun getWorkoutPlan(uid: String): Resource<QuerySnapshot> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkoutPlan(workoutPlanId: String, uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editWorkoutPlan(workoutPlanId: String, uid: String) {
        TODO("Not yet implemented")
    }
}