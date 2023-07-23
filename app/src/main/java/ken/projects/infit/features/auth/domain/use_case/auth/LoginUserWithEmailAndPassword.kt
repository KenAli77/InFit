package ken.projects.infit.features.auth.domain.use_case.auth

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.core.utils.Resource

/**
 * TODO
 * Remove AuthResult import and map response to custom class in repo
 */
class LoginUserWithEmailAndPassword(private val repository: AuthRepository) {

    suspend operator fun invoke(email:String,password:String): GenericResponse {
       val result = repository.loginUser(email = email, password = password)

        when (result){
            is Resource.Error -> {
                return GenericResponse(success = false, errorMessage = result.message)

            }
            is Resource.Success -> {
                return GenericResponse(success = true, data = result.data)
            }
        }
    }

}