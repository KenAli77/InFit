package ken.projects.infit.features.feature_auth.domain.use_case.validation

class ValidatePassword {

    companion object {
        const val PASSWORD_MIN_LENGTH = 8
    }

    operator fun invoke(password: String): ValidationResult {
        if (password.length < PASSWORD_MIN_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must have at least 8 characters"
            )
        }

        val containLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one letter and one digit"
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}