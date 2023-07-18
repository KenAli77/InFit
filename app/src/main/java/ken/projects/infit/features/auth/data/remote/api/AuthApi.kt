package ken.projects.infit.features.auth.data.remote.api

import ken.projects.infit.features.auth.data.remote.models.AuthResponse
import ken.projects.infit.features.auth.data.remote.models.EmailLogin
import ken.projects.infit.features.auth.data.remote.models.UserRegistration
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    companion object {
        const val REGISTER_API = "/auth/register"
        const val LOGIN_API = "/auth/login"
    }

    @POST(LOGIN_API)
    suspend fun loginUserWithEmail(@Body data:EmailLogin):AuthResponse

    @POST(REGISTER_API)
    suspend fun registerUser(@Body data:UserRegistration):AuthResponse

}