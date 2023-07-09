package ken.projects.infit.features.feature_auth.domain.use_case.validation

import ken.projects.infit.features.feature_auth.data.models.NewUser

class ArePasswordMatching() {


    operator fun invoke(password:String,confirmPassword:String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(successful = false, errorMessage = "Passwords are not matching")
        }

        return ValidationResult(
            successful = true
        )
    }

}