package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.R
import ken.projects.infit.core.utils.UiText

class ValidateTerms() {

    operator fun invoke(accepted: Boolean): ValidationResult {
        if (!accepted) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_terms_conditions)
            )
        }
        return ValidationResult(successful = true)
    }
}