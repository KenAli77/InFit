package ken.projects.infit.features.feature_auth.domain.use_case.validation

class ValidateUserName() {

    operator fun invoke(userName:String): ValidationResult {
        if (!userName.isNotEmpty()) {
            return ValidationResult(successful = false, errorMessage = "User name cannot be empty")
        }

        return ValidationResult(successful = true)
    }
}