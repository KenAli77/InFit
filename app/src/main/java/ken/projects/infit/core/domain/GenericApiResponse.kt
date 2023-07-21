package ken.projects.infit.core.domain


data class GenericApiResponse(
    val success:Boolean = false,
    val errorMessage: String? = null,
    val data:Any? = null

)
