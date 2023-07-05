package ken.projects.infit.feature_auth.domain.use_case

import ken.projects.infit.feature_auth.data.models.NewUser

class ArePasswordMatching(private val user:NewUser) {


    suspend operator fun invoke():Boolean {
        return user.password == user.repeatPassword
    }

}