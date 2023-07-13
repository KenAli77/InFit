package ken.projects.infit.features.auth.domain.use_case.validation

import ken.projects.infit.R
import ken.projects.infit.core.utils.UiText

class ArePasswordMatching() {


    operator fun invoke(password:String,confirmPassword:String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(successful = false, errorMessage = UiText.StringResource(R.string.password_not_matching))
        }

        return ValidationResult(
            successful = true
        )
    }

}