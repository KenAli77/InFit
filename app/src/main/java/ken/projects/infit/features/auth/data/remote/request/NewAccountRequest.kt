package ken.projects.infit.features.auth.data.remote.request

data class NewAccountRequest(
    val username:String,
    val email:String,
    val password:String,
)
