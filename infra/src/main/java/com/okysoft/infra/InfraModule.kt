package com.okysoft.infra

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.okysoft.data.WatchKind
import com.okysoft.infra.impl.*
import com.okysoft.infra.repository.EpisodeRepository
import com.okysoft.infra.repository.ReviewRepository
import com.okysoft.infra.repository.StaffRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class InfraModule {

    private val REST_END_POINT = "https://api.annict.com"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authRepository: com.okysoft.infra.repository.AuthRepository,
        dispatcher: ApplicationDispatcher
            ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(RequestInterceptor(authRepository))
                .addInterceptor(ResponseInterceptor(dispatcher))
        return builder.build()
    }

//    @Singleton
//    @Provides
//    fun provideGson(): Gson {
//        return GsonBuilder()
//            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .registerTypeAdapter(WatchKind::class.java, WatchKindAdapter)
//            .create() // NamingPoricy そ指定す
//    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        keyStoreManager: KeyStoreManager,
        application: Application
    ): com.okysoft.infra.repository.AuthRepository = AuthRepositoryImpl(keyStoreManager, application)

    @Singleton
    @Provides
    fun provideKeyStoreManager(application: Application) = KeyStoreManager(application)

    @Singleton
    @Provides
    fun provideOauthService(client: Retrofit): AnnictService.Oauth {
        return client
                .create(AnnictService.Oauth::class.java)
    }

    @Singleton
    @Provides
    fun provideWorkService(retrofit: Retrofit): AnnictService.Works {
        return WorkServiceImpl(retrofit)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): AnnictService.User {
        return UserServiceImpl(retrofit)
    }

    @Singleton
    @Provides
    fun provideEpisodeRepository(client: Retrofit): EpisodeRepository {
        return EpisodeRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideReviewRepository(client: Retrofit): ReviewRepository {
        return ReviewRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideRecordService(client: Retrofit): AnnictService.Record {
        return RecordServiceImpl(client)
    }

    @Singleton
    @Provides
    fun provideCastService(client: Retrofit): AnnictService.Cast {
        return CastServiceImpl(client)
    }

    @Singleton
    @Provides
    fun provideStaffRepository(client: Retrofit): StaffRepository {
        return StaffRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideMeService(client: Retrofit): AnnictService.Me {
        return MeServiceImpl(client)
    }

    @Singleton
    @Provides
    fun provideProgramService(client: Retrofit): AnnictService.Program {
        return ProgramServiceImpl(client)
    }

    @Singleton
    @Provides
    fun createRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(WatchKind::class.java, WatchKindAdapter)
            .create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(REST_END_POINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun createCoroutineContext(): CoroutineContext {
        return Dispatchers.Default
    }

}