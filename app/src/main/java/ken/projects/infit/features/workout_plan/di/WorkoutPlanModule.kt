package ken.projects.infit.features.workout_plan.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.features.auth.data.remote.repositories.AuthRepositoryImpl
import ken.projects.infit.features.auth.data.validators.AndroidEmailPatternValidator
import ken.projects.infit.features.auth.domain.repostitories.AuthRepository
import ken.projects.infit.features.auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.auth.domain.use_case.auth.CreateNewUser
import ken.projects.infit.features.auth.domain.use_case.auth.LoginUserWithEmailAndPassword
import ken.projects.infit.features.auth.domain.use_case.validation.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutPlanModule {

    @Provides
    @Singleton
    fun provideFirebase(): Firebase {
        return Firebase
    }


}