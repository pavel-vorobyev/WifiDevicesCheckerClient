package com.pavelvorobyev.wifidevicescheckerclient.view

interface MainView {

    fun setIsExists(isExists: Boolean)
    fun setIp(ip: String)
    fun setMac(mac: String)
    fun setInterface(dInterface: String)
    fun showError(reason: String)

}