package ken.projects.infit.features.workout_plan.domain.use_case.workout_plan

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateWorkoutPlan(private val repo: WorkoutPlanRepository) {

    suspend operator fun invoke(workout:WorkoutPlan):GenericResponse{

        val result = repo.createWorkoutPlan(workoutPlan = workout)

        return when(result){
            is Resource.Error -> {
                GenericResponse(success = false, errorMessage = result.message)
            }
            is Resource.Success -> {
                GenericResponse(success = true)
            }
        }

    }

}