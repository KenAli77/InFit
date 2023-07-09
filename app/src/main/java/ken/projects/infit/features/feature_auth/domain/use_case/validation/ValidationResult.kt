package ken.projects.infit.features.feature_auth.domain.use_case.validation

data class ValidationResult(
    val successful:Boolean,
    val errorMessage:String? = null
) {
}