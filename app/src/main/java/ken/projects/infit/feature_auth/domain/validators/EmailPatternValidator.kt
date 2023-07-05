package ken.projects.infit.feature_auth.domain.validators

interface EmailPatternValidator {

    fun isValidEmail(email:String):Boolean
}