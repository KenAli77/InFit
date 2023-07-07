package ken.projects.infit.features.feature_auth.domain.validators

interface EmailPatternValidator {

    fun isValidEmail(email:String):Boolean
}