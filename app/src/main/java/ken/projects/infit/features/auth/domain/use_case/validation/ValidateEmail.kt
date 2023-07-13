package ken.projects.infit.features.auth.domain.use_case.validation

import ken.projects.infit.R
import ken.projects.infit.core.utils.UiText
import ken.projects.infit.features.auth.domain.use_case.validation.validators.EmailPatternValidator

class ValidateEmail(private val validator: EmailPatternValidator) {

    operator fun invoke(email: String?): ValidationResult {
        if (email.isNullOrBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_empty_email),
            )
        }
        if (!validator.isValidEmail(email)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_email_not_valid),
            )
        }
        return ValidationResult(
            successful = true,
        )

    }
}

