package ken.projects.infit.features.auth.presentation.register.events.authentication

import ken.projects.infit.core.utils.UiText

sealed class SignUpAuthEvent {

    object Success: SignUpAuthEvent()
    data class Failure(val message:UiText?=null) : SignUpAuthEvent()

}