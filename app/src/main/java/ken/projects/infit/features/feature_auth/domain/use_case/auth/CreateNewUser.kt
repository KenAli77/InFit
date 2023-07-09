package ken.projects.infit.features.feature_auth.domain.use_case.auth

import ken.projects.infit.features.feature_auth.data.models.InvalidUserException
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository
import ken.projects.infit.util.Resource

class CreateNewUser(private val repository: AuthRepository) {


    @Throws(InvalidUserException::class)
    suspend operator fun invoke(userName:String,email:String,password:String): AuthResult {

        if(userName.isBlank()){
            throw InvalidUserException("You must enter a user name")
        }

        if(email.isBlank()){
            throw InvalidUserException("You must enter a email")
        }

        if(password.isBlank()){
            throw InvalidUserException("You must enter all required passwords")
        }

        val result = repository.createNewUser(userName = userName, userEmailAddress = email, userLoginPassword = password)

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