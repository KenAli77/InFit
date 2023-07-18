package ken.projects.infit.features.auth.domain.use_case.auth

import ken.projects.infit.core.domain.Response
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.auth.data.remote.models.InvalidUserException
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.util.Resource

class CreateNewUser(private val repository: AuthRepository) {


    @Throws(InvalidUserException::class)
    suspend operator fun invoke(userName:String,email:String,password:String): Response {

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
                return Response(success = false, errorMessage = result.message?.let {
                    UiText.DynamicString(
                        it
                    )
                })
            }
            is Resource.Loading -> {
                return Response()
            }
            is Resource.Success -> {
                return Response(success = true, data = result.data)
            }
        }

    }

}