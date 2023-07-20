package ken.projects.infit.core.data.response

data class ApiResponse<T> (
    val successful: Boolean,
    val message: String? = null,
    val data: T? = null
)
