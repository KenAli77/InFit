package ken.projects.infit.features.feature_auth.presentation.register.state

import com.google.firebase.auth.AuthResult

data class SignupState(
    val data: AuthResult? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val userName:String = "",
    val userNameError:String? = null,
    val email:String = "",
    val emailError:String? = null,
    val password:String = "",
    val passwordError:String? = null,
    val confirmPassword:String = "",
    val confirmPasswordError:String? = null,
    val acceptedTerms:Boolean = false,
    val termsError:String? = null,
)
