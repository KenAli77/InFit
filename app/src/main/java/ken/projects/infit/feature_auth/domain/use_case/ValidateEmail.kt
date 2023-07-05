package ken.projects.infit.feature_auth.domain.use_case

import ken.projects.infit.feature_auth.domain.validators.EmailPatternValidator

class ValidateEmail(private val validator: EmailPatternValidator) {

    suspend operator fun invoke(email:String):Boolean {
        return if (email.isBlank()) {
            false
        } else {
            validator.isValidEmail(email)
        }
    }

}