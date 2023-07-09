package ken.projects.infit.features.feature_auth.domain.use_case.validation

class ValidateTerms() {

    operator fun invoke(accepted:Boolean): ValidationResult {
        if(!accepted){
            return ValidationResult(successful = false, errorMessage = "Please accept terms and conditions")
        }
        return ValidationResult(successful = true)
    }
}