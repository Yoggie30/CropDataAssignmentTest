package `in`.cropdata.machine_test.yogesg_dhatrak.di

import `in`.cropdata.machine_test.yogesg_dhatrak.data.local.AppDatabase
import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.PlatformService
import `in`.cropdata.machine_test.yogesg_dhatrak.data.remote.RetrofitInstance
import `in`.cropdata.machine_test.yogesg_dhatrak.data.repository.DashboardRepository
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.NetworkHelper
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): RetrofitInstance = RetrofitInstance

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper =
        NetworkHelper(appContext)

    @Provides
    fun providePlatformService(retrofit: Retrofit): PlatformService =
        retrofit.create(PlatformService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePlatformsDao(db: AppDatabase) = db.platformsDao()

    @Singleton
    @Provides
    fun provideRepository(
        networkHelper: NetworkHelper,
        apiHelper: RetrofitInstance,
        db: AppDatabase
    ) = DashboardRepository(networkHelper, apiHelper, db)
}