package com.example.rxjavalecture.exercise.data.remote

import com.example.rxjavalecture.exercise.data.remote.login.LoginApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseURL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    private val okHttpClient: OkHttpClient by lazy {
        return@lazy OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val buildRetrofitClient: Retrofit by lazy {
        return@lazy Retrofit
            .Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val loginAPI: LoginApi by lazy {
        return@lazy buildRetrofitClient.create(LoginApi::class.java)
    }
}