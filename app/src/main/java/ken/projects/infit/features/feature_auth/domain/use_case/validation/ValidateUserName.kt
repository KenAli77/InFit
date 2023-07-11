package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.R
import ken.projects.infit.core.utils.UiText

class ValidateUserName() {

    operator fun invoke(userName:String): ValidationResult {
        if (!userName.isNotEmpty()) {
            return ValidationResult(successful = false, errorMessage = UiText.StringResource(R.string.error_empty_user_name))
        }

        return ValidationResult(successful = true)
    }
}