package ken.projects.infit.features.feature_auth.domain.use_case

class ValidateUserName() {

    operator fun invoke(userName:String):Boolean{
        return userName.isNotEmpty()
    }
}