package ken.projects.infit.data.repository

import android.app.Application
import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ken.projects.infit.data.models.User
import ken.projects.infit.domain.UserRepository
import ken.projects.infit.util.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val app: Application,
    private val auth: FirebaseAuth
) : UserRepository {

    private val fireStoreUserCollection = Firebase.firestore.collection("users")

    val currentUser = auth.currentUser

    override suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Resource<AuthResult> {

        return try {

            val registrationResult =
                auth.createUserWithEmailAndPassword(userEmailAddress, userLoginPassword)
                    .await()

            val userId = registrationResult.user?.uid!!
            val newUser = User(
                userName = userName,
                userEmail = userEmailAddress
            )
            fireStoreUserCollection.document(userId).set(newUser).await()

            Resource.Success(registrationResult)

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }


    }

    override suspend fun loginUser(email: String, password: String): Resource<AuthResult> {

        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Log.e("login", "logged in user ${result.user?.uid}")
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }


    }

    override suspend fun logOutUser() {
        auth.signOut()
    }


}