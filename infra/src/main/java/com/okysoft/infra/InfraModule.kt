package com.okysoft.infra

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.okysoft.data.WatchKind
import com.okysoft.infra.impl.*
import com.okysoft.infra.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
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
    fun provideOauthRepository(oauthService: AnnictService.Oauth, coroutineContext: CoroutineContext): OauthRepository {
        return OauthRepositoryImpl(oauthService, coroutineContext)
    }

    @Singleton
    @Provides
    fun provideWorkRepository(retrofit: Retrofit, apolloClient: ApolloClient): WorkRepository {
        return WorkRepositoryImpl(retrofit, apolloClient)
    }

    @Singleton
    @Provides
    fun provideUserRepository(authRepository: AuthRepository,retrofit: Retrofit): UserRepository {
        return UserRepositoryImpl(authRepository,retrofit)
    }

    @Singleton
    @Provides
    fun provideEpisodeRepository(client: Retrofit, apolloClient: ApolloClient): EpisodeRepository {
        return EpisodeRepositoryImpl(client, apolloClient)
    }

    @Singleton
    @Provides
    fun provideReviewRepository(client: Retrofit): ReviewRepository {
        return ReviewRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideRecordRepository(client: Retrofit): RecordRepository {
        return RecordRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideCastRepository(client: Retrofit): CastRepository {
        return CastRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideStaffRepository(client: Retrofit): StaffRepository {
        return StaffRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideMeRepository(client: Retrofit): MeRepository {
        return MeRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideProgramRepository(client: Retrofit): ProgramRepository {
        return ProgramRepositoryImpl(client)
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
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://api.annict.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun createCoroutineContext(): CoroutineContext {
        return Dispatchers.Default
    }

    @Singleton
    @Provides
    fun createWorkFeedRepository(apolloClient: ApolloClient): WorkFeedRepository {
        return WorkFeedRepositoryImpl(apolloClient)
    }

}