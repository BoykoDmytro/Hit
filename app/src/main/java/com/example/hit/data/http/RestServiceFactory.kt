package com.example.hit.data.http

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestServiceFactory {

    private const val CONNECTION_TIMEOUT = 60L
    private const val READ_TIMEOUT = CONNECTION_TIMEOUT
    private const val WRITE_TIMEOUT = CONNECTION_TIMEOUT

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
            .build()

    val GSON = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

    private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()

    fun <T> createService(clazz: Class<T>) = retrofit.create(clazz)
}