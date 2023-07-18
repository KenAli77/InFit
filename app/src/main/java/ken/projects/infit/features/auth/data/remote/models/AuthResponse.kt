package ken.projects.infit.features.auth.data.remote.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("_id")
    val id:String? = null,
    @SerializedName("authToken")
    val token: String? = null,
    val email: String? = null,
    @SerializedName("username")
    val userName:String? = null,
)
