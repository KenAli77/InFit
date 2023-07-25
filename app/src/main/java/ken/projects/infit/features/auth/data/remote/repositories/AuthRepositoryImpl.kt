package ken.projects.infit.features.auth.data.remote.repositories

import android.content.SharedPreferences
import android.util.Log
import ken.projects.infit.R
import ken.projects.infit.core.utils.Constants
import ken.projects.infit.features.auth.data.remote.api.AuthApi
import ken.projects.infit.features.auth.data.remote.response.AuthResponse
import ken.projects.infit.features.auth.data.remote.request.LoginRequest
import ken.projects.infit.features.auth.data.remote.request.NewAccountRequest
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.core.utils.Resource
import ken.projects.infit.core.utils.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPref:SharedPreferences
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
                    NewAccountRequest(
                        username = userName,
                        email = userEmailAddress,
                        password = userLoginPassword
                    )
                )

                if(response.success){

                }

                Resource.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(UiText.DynamicString(e.message.toString()))
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Resource<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(email = email,password = password)
                val response = api.loginUserWithEmail(request)
                if(!response.success){
                    Resource.Error(UiText.StringResource(R.string.error_unknown),null)
                }
                Log.e("RESPONSE",response.toString())
                response.token?.let { token->
                    Log.e("TOKEN","overriding token with ${token}")
                    sharedPref.edit()
                        .putString(Constants.KEY_JWT_TOKEN, token)
                        .apply()
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown),null)

                response.id?.let { id ->
                    Log.e("AUTH ID","overriding id with ${id}")
                    sharedPref.edit()
                        .putString(Constants.KEY_USER_ID, id)
                        .apply()
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown),null)
                Resource.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(UiText.DynamicString(e.message.toString()))
            }
        }
    }

    override suspend fun logOutUser() {
        withContext(Dispatchers.IO) {
        }
    }


}