package ken.projects.infit.features.auth.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.features.auth.data.repositories.AuthRepositoryImpl
import ken.projects.infit.features.auth.data.validators.AndroidEmailPatternValidator
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.features.auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.auth.domain.use_case.auth.CreateNewUser
import ken.projects.infit.features.auth.domain.use_case.auth.LoginUserWithEmailAndPassword
import ken.projects.infit.features.auth.domain.use_case.validation.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

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
    fun provideEmailValidator(): AndroidEmailPatternValidator {
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
            validateEmail = ValidateEmail(emailPatternValidator),
            validateUserName = ValidateUserName(),
            validateTerms = ValidateTerms(),
            validatePassword = ValidatePassword(),
        )
    }
}