package ken.projects.infit.features.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ken.projects.infit.features.dashboard.data.remote.api.DashboardApi
import ken.projects.infit.features.dashboard.data.remote.repositories.DashboardRepositoryImpl
import ken.projects.infit.features.dashboard.domain.repostitories.DashboardRepository
import ken.projects.infit.features.dashboard.domain.use_case.DashboardUseCases
import ken.projects.infit.features.dashboard.domain.use_case.GetWorkouts
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
    @Provides
    @Singleton
    fun provideDashboardApi(client: OkHttpClient): DashboardApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("http://192.168.1.7:8080/")
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDashboardRepository(api: DashboardApi): DashboardRepository {
        return DashboardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDashboardUseCases(repo:DashboardRepository):DashboardUseCases{
        return DashboardUseCases(getWorkouts = GetWorkouts(repo))
    }
}