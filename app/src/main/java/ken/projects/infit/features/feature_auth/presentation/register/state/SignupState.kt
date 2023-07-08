package ken.projects.infit.features.feature_auth.presentation.register.state

import com.google.firebase.auth.AuthResult

data class SignupState(
    val data: AuthResult? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val navigateTo:String? = null,
)
