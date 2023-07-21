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
import ken.projects.infit.features.workout_plan.data.remote.api.WorkoutPlanApi
import ken.projects.infit.features.workout_plan.data.remote.repositories.WorkoutPlanRepositoryImpl
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutPlanModule {

    @Provides
    @Singleton
    fun provideWorkoutPlanApi(): WorkoutPlanApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://192.168.1.7:8080/")
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideWorkoutPlanRepository(api:WorkoutPlanApi):WorkoutPlanRepository {
        return WorkoutPlanRepositoryImpl(api)
    }
}