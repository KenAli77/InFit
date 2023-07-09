package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.features.feature_auth.domain.use_case.validation.validators.EmailPatternValidator

class ValidateEmail(private val validator: EmailPatternValidator) {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email can't be blank"
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

