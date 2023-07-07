package ken.projects.infit.features.feature_auth.domain.use_case

import ken.projects.infit.features.feature_auth.data.models.NewUser

class ArePasswordMatching() {


    suspend operator fun invoke(user: NewUser):Boolean {
        return user.password == user.repeatPassword
    }

}