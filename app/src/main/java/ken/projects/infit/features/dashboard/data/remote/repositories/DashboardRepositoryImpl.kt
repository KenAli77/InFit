package ken.projects.infit.features.dashboard.data.remote.repositories

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.dashboard.data.remote.api.DashboardApi
import ken.projects.infit.features.dashboard.domain.repostitories.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(private val api:DashboardApi):DashboardRepository {
    override suspend fun getWorkoutPlans(): Resource<GenericResponse> {
     return withContext(Dispatchers.IO){
          try {
              val response = api.getWorkoutPlans()

              if(response.success){
                  Resource.Success(data = response)
              } else {
                  Resource.Error(message = response.errorMessage!!)
              }
          } catch (e:Exception){
              Resource.Error(message = UiText.DynamicString(e.message.toString()),null)
          }
        }
    }

    override suspend fun logOutUser() {
        TODO("Not yet implemented")
    }
}