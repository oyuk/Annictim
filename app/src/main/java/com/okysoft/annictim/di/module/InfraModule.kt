package com.okysoft.annictim.di.module

import com.apollographql.apollo.ApolloClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.okysoft.annictim.*
import com.okysoft.annictim.api.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class InfraModule {

    private val REST_END_POINT = "https://api.annict.com"
    private val GRAPH_QL_ENDPOINT = "https://api.annict.com/graphql"

    @Singleton
    @Provides
    fun provideOkHttpClient(
            authRepository: AuthRepository,
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

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create() // NamingPoricy そ指定す
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
            keyStoreManager: KeyStoreManager,
            application: AnnictimApplication
    ): AuthRepository = AuthRepositoryImpl(keyStoreManager, application)

    @Singleton
    @Provides
    fun provideKeyStoreManager(application: AnnictimApplication) = KeyStoreManager(application)

    @Singleton
    @Provides
    fun provideOauthService(@Named("coroutines") client: Retrofit): AnnictService.Oauth {
        return client
                .create(AnnictService.Oauth::class.java)
    }

    @Singleton
    @Provides
    fun provideWorkService(client: ApolloClient, retrofit: Retrofit): AnnictService.Works {
        return WorkServiceImpl(client, retrofit)
    }

    @Singleton
    @Provides
    fun provideUserService(client: ApolloClient, @Named("coroutines") retrofit: Retrofit): AnnictService.User {
        return UserServiceImpl(client, retrofit)
    }

    @Singleton
    @Provides
    fun provideEpisodeService(client: Retrofit): AnnictService.Episode {
        return EpisodeServiceImpl(client)
    }

    @Singleton
    @Provides
    fun provideReviewService(client: Retrofit): AnnictService.Review {
        return ReviewServiceImpl(client)
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
    fun provideStaffService(client: Retrofit): AnnictService.Staff {
        return StaffServiceImpl(client)
    }

    @Singleton
    @Provides
    fun provideMeService(@Named("coroutines") client: Retrofit): AnnictService.Me {
        return MeServiceImpl(client)
    }

    @Singleton
    @Provides
    fun createRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(REST_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    @Named("coroutines")
    fun createCoroutinesRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(REST_END_POINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun createCoroutineContext(): CoroutineContext {
        return Dispatchers.Default
    }

    @Singleton
    @Provides
    fun createApollo(client: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(GRAPH_QL_ENDPOINT)
                .okHttpClient(client)
                .build();
    }

}