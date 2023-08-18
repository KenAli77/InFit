package ken.projects.infit.features.dashboard.domain.repostitories

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.core.utils.Resource

interface DashboardRepository {

    suspend fun getWorkoutPlans(
    ): Resource<GenericResponse>



    suspend fun logOutUser()
}