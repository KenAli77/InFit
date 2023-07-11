package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.R
import ken.projects.infit.core.utils.UiText

class ValidatePassword {

    companion object {
        const val PASSWORD_MIN_LENGTH = 8
    }

    operator fun invoke(password: String): ValidationResult {
        if (password.length < PASSWORD_MIN_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_password_characters_limit)
            )
        }

        val containLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_password_digit_letter)
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}