package ken.projects.infit.features.auth.data.remote.models

data class NewUser(
    val userEmail:String,
    val userName:String,
    val password:String,
    val confirmPassword:String,
)
