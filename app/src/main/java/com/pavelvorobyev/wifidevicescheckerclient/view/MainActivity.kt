package com.pavelvorobyev.wifidevicescheckerclient.view

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.widget.Toast
import com.pavelvorobyev.wifidevicescheckerclient.R
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenterImpl(this)
        presenter.init(applicationContext)

        callBtnView.setOnClickListener {
            presenter.getInfo(hostInputView.text.toString(), portInputView.text.toString(),
                macInputView.text.toString())
        }
    }

    @MainThread
    override fun setIsExists(isExists: Boolean) {
        isExistsView.text = "Is exists $isExists"
    }

    @MainThread
    override fun setIp(ip: String) {
        ipView.text = "IP: $ip"
    }

    @MainThread
    override fun setMac(mac: String) {
        macView.text = "Mac: $mac"
    }

    @MainThread
    override fun setInterface(dInterface: String) {
        interfaceView.text = "Interface: $dInterface"
    }

    @MainThread
    override fun showError(reason: String) {
        Toast.makeText(this, "Some error has occurred: $reason", Toast.LENGTH_SHORT).show()
    }
}
