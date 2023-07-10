package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.features.feature_auth.domain.use_case.validation.validators.EmailPatternValidator

class ValidateEmail(private val validator: EmailPatternValidator) {

    operator fun invoke(email: String?): ValidationResult {
        if (email.isNullOrBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email can't be empty"
            )
        }
        if (!validator.isValidEmail(email)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email is not valid",
            )
        }
        return ValidationResult(
            successful = true,
        )

    }
}

