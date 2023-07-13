package ken.projects.infit.features.auth.data.models

import java.lang.Exception


data class User(
    val userEmail:String? = null,
    val userName:String? = null
)

class InvalidUserException(message:String): Exception()