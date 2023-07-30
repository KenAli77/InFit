package ken.projects.infit.features.workout_plan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.features.auth.domain.use_case.validation.*
import ken.projects.infit.features.workout_plan.data.remote.api.WorkoutPlanApi
import ken.projects.infit.features.workout_plan.data.remote.repositories.WorkoutPlanRepositoryImpl
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository
import ken.projects.infit.features.workout_plan.domain.use_case.CreateWorkoutPlan
import ken.projects.infit.features.workout_plan.domain.use_case.DeleteWorkoutPlan
import ken.projects.infit.features.workout_plan.domain.use_case.EditWorkoutPlan
import ken.projects.infit.features.workout_plan.domain.use_case.WorkoutPlanUseCases
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
            .baseUrl("http://192.168.1.5:8080/")
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideWorkoutPlanRepository(api:WorkoutPlanApi):WorkoutPlanRepository {
        return WorkoutPlanRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideWorkoutPlanUseCases(repo:WorkoutPlanRepository):WorkoutPlanUseCases{
        return WorkoutPlanUseCases(
            createWorkoutPlan = CreateWorkoutPlan(repo),
            editWorkoutPlan = EditWorkoutPlan(repo),
            deleteWorkoutPlan = DeleteWorkoutPlan(repo)
        )
    }
}