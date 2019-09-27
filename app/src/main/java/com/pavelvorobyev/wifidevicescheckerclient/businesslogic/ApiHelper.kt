package com.pavelvorobyev.wifidevicescheckerclient.businesslogic

import android.content.SharedPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper(private val preferences: SharedPreferences?) {

    private val httpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(DynamicUrlInterceptor(preferences!!))
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://url.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val services = retrofit.create(Services::class.java)!!

}