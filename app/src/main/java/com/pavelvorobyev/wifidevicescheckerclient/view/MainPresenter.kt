package com.pavelvorobyev.wifidevicescheckerclient.view

import android.content.Context

interface MainPresenter {

    val view: MainView

    fun init(context: Context)
    fun getInfo(host: String, port: String, mac: String)

}