package ken.projects.infit.features.auth.domain.use_case

import ken.projects.infit.features.auth.domain.use_case.auth.CreateNewUser
import ken.projects.infit.features.auth.domain.use_case.auth.LoginUserWithEmailAndPassword
import ken.projects.infit.features.auth.domain.use_case.validation.*

data class AuthUseCases(
    val arePasswordMatching: ArePasswordMatching,
    val createNewUser: CreateNewUser,
    val loginUserWithEmailAndPassword: LoginUserWithEmailAndPassword,
    val validateEmail: ValidateEmail,
    val validateUserName: ValidateUserName,
    val validateTerms: ValidateTerms,
    val validatePassword: ValidatePassword
)