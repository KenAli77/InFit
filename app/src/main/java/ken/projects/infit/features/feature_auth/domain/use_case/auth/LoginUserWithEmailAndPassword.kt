package ken.projects.infit.features.feature_auth.domain.use_case.auth

import com.google.firebase.auth.AuthResult
import ken.projects.infit.features.feature_auth.data.models.EmailLogin
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository
import ken.projects.infit.util.Resource

/**
 * TODO
 * Remove AuthResult import and map response to custom class in repo
 */
class LoginUserWithEmailAndPassword(private val repository: AuthRepository) {

    suspend operator fun invoke(credentials: EmailLogin):Resource<AuthResult>{
       return repository.loginUser(email = credentials.email, password = credentials.password)
    }

}