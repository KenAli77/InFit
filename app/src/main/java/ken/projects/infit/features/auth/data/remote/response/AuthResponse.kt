package ken.projects.infit.features.auth.data.remote.response

data class AuthResponse(
    val success: Boolean = false,
    val id:String? = null,
    val authToken: String? = null,
)
