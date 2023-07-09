package ken.projects.infit.features.feature_auth.domain.use_case.validation.validators

interface EmailPatternValidator {

    fun isValidEmail(email:String):Boolean
}