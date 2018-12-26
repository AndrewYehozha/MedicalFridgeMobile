package com.example.andri.medicalfridge.network

import com.example.andri.medicalfridge.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network {

    companion object {

        fun providesHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = OkHttpClient.Builder()
            client.readTimeout(60, TimeUnit.SECONDS)
            client.writeTimeout(60, TimeUnit.SECONDS)
            client.connectTimeout(60, TimeUnit.SECONDS)
            client.addInterceptor(loggingInterceptor)

            return client.build()
        }

        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

        fun createApi() = provideApi(provideRetrofit(providesHttpClient()))

    }

}