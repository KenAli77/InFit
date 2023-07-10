package ken.projects.infit.features.feature_auth.presentation.login.events.authentication

sealed class LoginAuthEvent {

    object Success: LoginAuthEvent()
    data class Failure(val reason: String?=null) : LoginAuthEvent()

}

sealed class LoginFailure {
    object InvalidCredentials: LoginFailure()
    data class LockedAccount(val message:String): LoginFailure()
}