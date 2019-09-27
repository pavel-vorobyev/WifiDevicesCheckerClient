package com.pavelvorobyev.wifidevicescheckerclient.view

import android.content.Context
import android.content.SharedPreferences
import com.pavelvorobyev.wifidevicescheckerclient.BuildConfig
import com.pavelvorobyev.wifidevicescheckerclient.businesslogic.ApiHelper
import com.pavelvorobyev.wifidevicescheckerclient.businesslogic.model.DeviceInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl(override val view: MainView) : MainPresenter {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var apiHelper: ApiHelper

    override fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            BuildConfig.APPLICATION_ID + "_shared_preferences_filename",
            Context.MODE_PRIVATE)
        apiHelper = ApiHelper(sharedPreferences)
    }

    override fun getInfo(host: String, port: String, mac: String) {
        sharedPreferences.edit()
            .putString("url", "$host:$port")
            .putString("mac", mac)
            .apply()

        apiHelper.services.getDeviceInfo()
            .enqueue(object : Callback<DeviceInfoResponse> {

                override fun onResponse(call: Call<DeviceInfoResponse>,
                                        response: Response<DeviceInfoResponse>) {

                    when (response.isSuccessful) {
                        true -> processResponse(response.body()!!)
                        false -> view.showError(response.code().toString())
                    }
                }

                override fun onFailure(call: Call<DeviceInfoResponse>, t: Throwable) {
                    view.showError(t.message.toString())
                }
            })
    }

    fun processResponse(response: DeviceInfoResponse) {
        view.setIsExists(response.exists)
        view.setIp(response.ip)
        view.setMac(response.mac)
        view.setInterface(response.dInterface)
    }

}