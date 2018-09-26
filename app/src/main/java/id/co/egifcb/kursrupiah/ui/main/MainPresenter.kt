package id.co.egifcb.kursrupiah.ui.main

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import id.co.egifcb.kursrupiah.model.Data

class MainPresenter(private val mainView: MainView) {
    fun getKurs() {
        mainView.showLoading()

        AndroidNetworking.get("http://www.adisurya.net/kurs-bca/get?MataUang=USD")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(Data::class.java, object : ParsedRequestListener<Data> {
                    override fun onResponse(response: Data?) {
                        mainView.onResponse(response?.data?.usd?.jual.toString(), response?.data?.usd?.beli.toString(), response?.time.toString())
                        mainView.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        mainView.onError(anError?.message)
                        mainView.hideLoading()
                    }

                })
    }
}