package ken.projects.infit.features.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val success: Boolean = false,
    @SerializedName("_id")
    val id:String? = null,
    @SerializedName("authToken")
    val token: String? = null,
)
