package ken.projects.infit.features.feature_auth.presentation.login.state

import com.google.firebase.auth.AuthResult
import ken.projects.infit.core.utils.UiText

data class LoginState(
    val data: AuthResult? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: UiText? = null,
    val email:String = "",
    val emailError:UiText? = null,
    val password:String = "",
    val passwordError:UiText?=null,
)

