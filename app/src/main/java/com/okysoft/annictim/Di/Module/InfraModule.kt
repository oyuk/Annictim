package com.okysoft.annictim.Di.Module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.RequestInterceptor
import com.okysoft.annictim.AnnictimApplication
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.AuthRepositoryImpl
import com.okysoft.annictim.KeyStoreManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class InfraModule {

    private val ENDPOINT = "https://api.annict.com"

    @Singleton
    @Provides
    fun provideOkHttpClient(
            authRepository: AuthRepository
            ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(RequestInterceptor(authRepository))
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
    fun provideOauthService(client: OkHttpClient, gson: Gson): AnnictService.Oauth {
        return createRetrofit(client,gson)
                .create(AnnictService.Oauth::class.java)
    }

    private fun createRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}