package ken.projects.infit.features.feature_auth.domain.repostitories

import com.google.firebase.auth.AuthResult
import ken.projects.infit.util.Resource

interface AuthRepository {

    suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Resource<AuthResult>


    suspend fun loginUser(email: String, password: String): Resource<AuthResult>

    suspend fun logOutUser()
}