package ken.projects.infit.features.feature_auth.domain.use_case.auth

import ken.projects.infit.core.utils.UiText

data class AuthResult(
    val success:Boolean = false,
    val errorMessage:UiText? = null,
    val data:Any? = null
) {
}