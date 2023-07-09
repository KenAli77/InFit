package ken.projects.infit.features.feature_auth.domain.use_case.auth

data class AuthResult(
    val success:Boolean = false,
    val errorMessage:String? = null,
    val data:Any? = null
) {
}