package ken.projects.infit.features.auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class NewAccountRequest(
    val username:String,
    val email:String,
    val password:String,

    )
