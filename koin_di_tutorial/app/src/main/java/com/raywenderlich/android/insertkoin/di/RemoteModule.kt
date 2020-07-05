package com.raywenderlich.android.insertkoin.di

import com.raywenderlich.android.insertkoin.di.RemoteProperties.BASE_URL
import com.raywenderlich.android.insertkoin.repository.RemoteDataSource
import org.koin.dsl.module.applicationContext
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteProperties {
    const val BASE_URL = "BASE_URL"
}

fun createBaseUrl() = "https://api.githun.com"
fun createConvecterFactory(): Converter.Factory {
    return GsonConverterFactory.create()
}

inline fun <reified T> createWebService(baseUrl: String, factory: Converter.Factory): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .build()
    return retrofit.create(T::class.java)
}

val remoteModule = applicationContext {
    bean(BASE_URL) { createBaseUrl() }
    bean { createConvecterFactory() }
    bean { createWebService<RemoteDataSource>(get(BASE_URL), get()) }
}