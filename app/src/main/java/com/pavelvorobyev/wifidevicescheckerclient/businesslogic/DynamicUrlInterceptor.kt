package com.pavelvorobyev.wifidevicescheckerclient.businesslogic

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DynamicUrlInterceptor(private val preferences: SharedPreferences): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var url = preferences.getString("url", "")
        val mac = preferences.getString("mac", "")
        url += "/check?mac=$mac"

        var request = chain.request()
        request = request.newBuilder()
            .url(url!!)
            .build()

        return chain.proceed(request)
    }
}