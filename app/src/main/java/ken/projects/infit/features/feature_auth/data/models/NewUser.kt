package ken.projects.infit.features.feature_auth.data.models

data class NewUser(
    val userEmail:String,
    val userName:String,
    val password:String,
    val confirmPassword:String,
)
