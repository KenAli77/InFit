package ken.projects.infit.features.auth.data.validators

import ken.projects.infit.features.auth.domain.use_case.validation.validators.EmailPatternValidator

class MockAndroidEmailPatternValidator : EmailPatternValidator {

    var isValid: Boolean = false

    override fun isValidEmail(email: String): Boolean {
        return isValid
    }

    fun setIsValid(valid:Boolean){
        isValid = valid
    }


}