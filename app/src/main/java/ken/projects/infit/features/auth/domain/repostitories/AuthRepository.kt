package ken.projects.infit.features.auth.domain.repostitories

import ken.projects.infit.features.auth.data.remote.response.AuthResponse
import ken.projects.infit.util.Resource

interface AuthRepository {

    suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Resource<AuthResponse>


    suspend fun loginUser(email: String, password: String): Resource<AuthResponse>

    suspend fun logOutUser()
}