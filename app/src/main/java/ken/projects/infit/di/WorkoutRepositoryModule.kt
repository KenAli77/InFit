package ken.projects.infit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.data.repository.WorkoutRepositoryImpl
import ken.projects.infit.domain.WorkoutRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkoutRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository (workoutRepositoryImpl: WorkoutRepositoryImpl): WorkoutRepository
}