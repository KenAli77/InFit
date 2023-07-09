package ken.projects.infit.features.feature_auth.presentation.register.events.authentication

sealed class SignUpAuthEvent {

    object Success: SignUpAuthEvent()
    data class Failure(val message:String?=null) : SignUpAuthEvent()

}