package ken.projects.infit.features.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("authToken")
    val authToken: String? = null,
) : java.io.Serializable
