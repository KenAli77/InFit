package ken.projects.infit.features.auth.domain.use_case.validation

import ken.projects.infit.core.utils.UiText

data class ValidationResult(
    val successful:Boolean,
    val errorMessage:UiText? = null
) {
}