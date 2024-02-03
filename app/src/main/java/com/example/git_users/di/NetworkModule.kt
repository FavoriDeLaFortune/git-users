package com.example.git_users.di

import com.example.git_users.BuildConfig
import com.example.git_users.network.GithubApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { interceptor ->
            val request = interceptor.request().newBuilder()
                .addHeader(BuildConfig.ACCEPT_FIELD, BuildConfig.ACCEPT_PARAMETER)
                .addHeader(BuildConfig.API_VERSION_FIELD, BuildConfig.API_VERSION_PARAMETER)
                .addHeader(BuildConfig.API_AUTHORIZATION_FIELD, BuildConfig.API_AUTHORIZATION_PARAMETER)
                .build()
            return@Interceptor interceptor.proceed(request)
        }
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

}