package ken.projects.infit.features.feature_auth.domain.use_case

import ken.projects.infit.features.feature_auth.data.models.InvalidUserException
import ken.projects.infit.features.feature_auth.data.models.NewUser
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository

class CreateNewUser(private val repository: AuthRepository) {


    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: NewUser) {

        if(user.userName.isBlank()){
            throw InvalidUserException("You must enter a user name")
        }

        if(user.userEmail.isBlank()){
            throw InvalidUserException("You must enter a email")
        }

        if(user.password.isBlank() || user.confirmPassword.isBlank()){
            throw InvalidUserException("You must enter all required passwords")
        }

        repository.createNewUser(userName = user.userName, userEmailAddress = user.userEmail, userLoginPassword = user.password)

    }

}