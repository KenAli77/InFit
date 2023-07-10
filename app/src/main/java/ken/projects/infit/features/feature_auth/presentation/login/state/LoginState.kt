package ken.projects.infit.features.feature_auth.presentation.login.state

import com.google.firebase.auth.AuthResult

data class LoginState(
    val data: AuthResult? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val navigateTo:String? = null,
)

