package ken.projects.infit.features.auth.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.features.auth.data.remote.api.AuthApi
import ken.projects.infit.features.auth.data.remote.repositories.AuthRepositoryImpl
import ken.projects.infit.features.auth.data.validators.AndroidEmailPatternValidator
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.features.auth.domain.use_case.*
import ken.projects.infit.features.auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.auth.domain.use_case.auth.CreateNewUser
import ken.projects.infit.features.auth.domain.use_case.auth.LoginUserWithEmailAndPassword
import ken.projects.infit.features.auth.domain.use_case.validation.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://192.168.1.5:8080/")
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: AuthApi, sharedPreferences: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(auth, sharedPreferences)
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