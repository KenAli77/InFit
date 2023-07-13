package ken.projects.infit.features.auth.presentation.register.state

import com.google.firebase.auth.AuthResult
import ken.projects.infit.core.utils.UiText

data class SignupState(
    val data: AuthResult? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: UiText? = null,
    val userName:String = "",
    val userNameError:UiText? = null,
    val email:String = "",
    val emailError:UiText? = null,
    val password:String = "",
    val passwordError:UiText? = null,
    val confirmPassword:String = "",
    val confirmPasswordError:UiText? = null,
    val acceptedTerms:Boolean = false,
    val termsError:UiText? = null,
)
