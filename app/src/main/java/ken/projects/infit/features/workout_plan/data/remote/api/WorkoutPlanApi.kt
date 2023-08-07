package ken.projects.infit.features.workout_plan.data.remote.api

import ken.projects.infit.core.domain.GenericApiResponse
import ken.projects.infit.features.workout_plan.data.remote.request.CreateWorkoutPlanRequest
import ken.projects.infit.features.workout_plan.data.remote.request.EditWorkoutRequest
import ken.projects.infit.features.workout_plan.data.remote.response.WorkoutPlanResponse
import ken.projects.infit.features.workout_plan.data.remote.response.WorkoutPlansResponse
import retrofit2.http.*

interface WorkoutPlanApi {

    companion object {
        const val WORKOUT_PLAN_API = "api/app/workoutPlan"
    }

    @POST(WORKOUT_PLAN_API)
    suspend fun createWorkout(@Body data: CreateWorkoutPlanRequest):GenericApiResponse

    @GET(WORKOUT_PLAN_API)
    suspend fun getWorkoutPlans():WorkoutPlansResponse

    @GET("$WORKOUT_PLAN_API/{id}")
    suspend fun getWorkoutPlanById(@Path("id") id:String):WorkoutPlanResponse

    @PUT(WORKOUT_PLAN_API)
    suspend fun editWorkout(@Body data: EditWorkoutRequest):GenericApiResponse

    @DELETE("$WORKOUT_PLAN_API/{id}")
    suspend fun deleteWorkout(@Path("id") id:String): GenericApiResponse

}