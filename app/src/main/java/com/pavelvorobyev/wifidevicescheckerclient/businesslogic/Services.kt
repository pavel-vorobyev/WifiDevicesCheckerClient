package com.pavelvorobyev.wifidevicescheckerclient.businesslogic

import retrofit2.Call
import com.pavelvorobyev.wifidevicescheckerclient.businesslogic.model.DeviceInfoResponse
import retrofit2.http.GET

interface Services {

    @GET("/path")
    fun getDeviceInfo(): Call<DeviceInfoResponse>

}