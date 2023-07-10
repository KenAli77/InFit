package ken.projects.infit.features.feature_auth.domain.use_case.auth

import ken.projects.infit.features.feature_auth.data.models.EmailLogin
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository
import ken.projects.infit.util.Resource

/**
 * TODO
 * Remove AuthResult import and map response to custom class in repo
 */
class LoginUserWithEmailAndPassword(private val repository: AuthRepository) {

    suspend operator fun invoke(credentials: EmailLogin):AuthResult{
       val result = repository.loginUser(email = credentials.email, password = credentials.password)

        when (result){
            is Resource.Error -> {
                return AuthResult(success = false, errorMessage = result.message)
            }
            is Resource.Loading -> {
                return AuthResult()
            }
            is Resource.Success -> {
                return AuthResult(success = true, data = result.data)
            }
        }
    }

}