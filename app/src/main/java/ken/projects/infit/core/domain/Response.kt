package ken.projects.infit.core.domain

import ken.projects.infit.core.utils.UiText

data class Response(
    val success:Boolean = false,
    val errorMessage:UiText? = null,
    val data:Any? = null
) {
}