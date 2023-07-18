package ken.projects.infit.features.auth.data.remote.models

import com.google.gson.annotations.SerializedName

data class UserRegistration(
    @SerializedName("username")
    val userName:String,
    val email:String,
    val password:String,
)
