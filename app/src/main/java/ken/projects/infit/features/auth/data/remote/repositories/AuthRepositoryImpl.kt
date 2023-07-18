package ken.projects.infit.features.auth.data.remote.repositories

import ken.projects.infit.features.auth.data.remote.api.AuthApi
import ken.projects.infit.features.auth.data.remote.models.AuthResponse
import ken.projects.infit.features.auth.data.remote.models.EmailLogin
import ken.projects.infit.features.auth.data.remote.models.UserRegistration
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {


    override suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Resource<AuthResponse> {
        return withContext(Dispatchers.IO)
        {
            try {

                val response = api.registerUser(
                    UserRegistration(
                        userName = userName,
                        email = userEmailAddress,
                        password = userLoginPassword
                    )
                )

                Resource.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message.toString())
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Resource<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.loginUserWithEmail(EmailLogin(email = email,password = password))
                Resource.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message.toString())
            }
        }
    }

    override suspend fun logOutUser() {
        withContext(Dispatchers.IO) {
        }
    }


}