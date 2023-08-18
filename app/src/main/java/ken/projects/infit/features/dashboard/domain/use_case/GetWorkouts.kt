package ken.projects.infit.features.dashboard.domain.use_case

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.features.dashboard.domain.repostitories.DashboardRepository

class GetWorkouts(private val repo:DashboardRepository) {

   suspend operator fun invoke():GenericResponse{
       val response = repo.getWorkoutPlans()

       when (response){
           is Resource.Error -> {
               return GenericResponse(success = false)
           }
           is Resource.Success -> {
               return GenericResponse(success = true, data = response.data)
           }
       }
    }

}