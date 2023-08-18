package ken.projects.infit.features.dashboard.data.remote.api

import ken.projects.infit.core.domain.GenericResponse
import retrofit2.http.GET


interface DashboardApi {
    companion object {
        const val WORKOUT_PLAN_API = "api/app/workoutPlan"
    }
    @GET(WORKOUT_PLAN_API)
    suspend fun getWorkoutPlans(): GenericResponse
}

