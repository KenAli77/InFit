package ken.projects.infit.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Provides
import ken.projects.infit.features.feature_auth.data.repositories.AuthRepositoryImpl
import ken.projects.infit.features.feature_auth.data.validators.AndroidEmailPatternValidator
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository
import ken.projects.infit.features.feature_auth.domain.use_case.*
import ken.projects.infit.features.feature_auth.domain.validators.EmailPatternValidator
import javax.inject.Singleton

object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideEmailValidator(): EmailPatternValidator {
        return AndroidEmailPatternValidator()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        authRepository: AuthRepository,
        emailPatternValidator: AndroidEmailPatternValidator
    ): AuthUseCases {
        return AuthUseCases(
            arePasswordMatching = ArePasswordMatching(),
            createNewUser = CreateNewUser(authRepository),
            loginUserWithEmailAndPassword = LoginUserWithEmailAndPassword(authRepository),
            validateEmail = ValidateEmail(emailPatternValidator)
        )
    }

}