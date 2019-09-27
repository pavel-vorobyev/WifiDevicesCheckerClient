package com.pavelvorobyev.wifidevicescheckerclient.businesslogic.model

import com.google.gson.annotations.SerializedName

data class DeviceInfoResponse (

    @SerializedName("exists")
    val exists: Boolean,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("mac")
    val mac: String,
    @SerializedName("interface")
    val dInterface: String

)