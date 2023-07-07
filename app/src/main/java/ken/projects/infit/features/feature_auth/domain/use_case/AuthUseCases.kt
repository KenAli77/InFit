package ken.projects.infit.features.feature_auth.domain.use_case

data class AuthUseCases(
    val arePasswordMatching: ArePasswordMatching,
    val createNewUser: CreateNewUser,
    val loginUserWithEmailAndPassword: LoginUserWithEmailAndPassword,
    val validateEmail: ValidateEmail
)