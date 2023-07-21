package ken.projects.infit.features.workout_plan.data.remote.repositories

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import ken.projects.infit.R
import ken.projects.infit.data.models.Workout
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.data.remote.api.WorkoutPlanApi
import ken.projects.infit.features.workout_plan.data.remote.request.CreateWorkoutRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class WorkoutPlanRepositoryImpl @Inject constructor(private val api: WorkoutPlanApi) :
    WorkoutPlanRepository {

    override suspend fun getWorkoutPlan(workoutPlanId: String): Resource<WorkoutPlan> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkoutPlan(workoutPlanId: String): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {

                val result = api.deleteWorkout(workoutPlanId)

                if (result.success) {
                    Resource.Success(Unit)
                } else {
                    result.errorMessage?.let {
                        Resource.Error(message = UiText.DynamicString(it))
                    } ?: Resource.Error(message = UiText.StringResource(R.string.error_unknown))
                }
            } catch (e: IOException) {
                Resource.Error(message = UiText.StringResource(R.string.error_couldnt_reach_server))
            } catch (e: HttpException) {
                Resource.Error(message = UiText.StringResource(R.string.oops_something_went_wrong))
            }
        }
    }

    override suspend fun editWorkoutPlan(workoutPlanId: String): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {

                val result = api.deleteWorkout(workoutPlanId)

                if (result.success) {
                    Resource.Success(Unit)
                } else {
                    result.errorMessage?.let {
                        Resource.Error(message = UiText.DynamicString(it))
                    } ?: Resource.Error(message = UiText.StringResource(R.string.error_unknown))
                }
            } catch (e: IOException) {
                Resource.Error(message = UiText.StringResource(R.string.error_couldnt_reach_server))
            } catch (e: HttpException) {
                Resource.Error(message = UiText.StringResource(R.string.oops_something_went_wrong))
            }
        }
    }

    override suspend fun createWorkoutPlan(workoutPlan: WorkoutPlan): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {

                val request = workoutPlan.toWorkoutRequest()

                if(request == null){
                    Resource.Error(message = UiText.StringResource(R.string.error_unknown),null)
                }


                val result = api.createWorkout(request!!)

                if (result.success) {
                    Resource.Success(Unit)
                } else {
                    result.errorMessage?.let {
                        Resource.Error(message = UiText.DynamicString(it))
                    } ?: Resource.Error(message = UiText.StringResource(R.string.error_unknown))
                }
            } catch (e: IOException) {
                Resource.Error(message = UiText.StringResource(R.string.error_couldnt_reach_server))
            } catch (e: HttpException) {
                Resource.Error(message = UiText.StringResource(R.string.oops_something_went_wrong))
            }
        }
    }

}