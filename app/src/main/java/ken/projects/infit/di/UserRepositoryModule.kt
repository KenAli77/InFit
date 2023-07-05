package ken.projects.infit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.feature_auth.data.repositories.AuthRepositoryImpl
import ken.projects.infit.feature_auth.domain.repostitories.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: AuthRepositoryImpl): AuthRepository

}
