package com.example.gitapp.di

import com.example.gitapp.data.remote.api.ApiService
import com.example.gitapp.utils.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    factory { provideUseApi(get()) }
}

private fun provideGson(): Gson {
    return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}

private fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    return okHttpClientBuilder.build()
}

private fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()
}

private fun provideUseApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)