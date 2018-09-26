package id.co.egifcb.kursrupiah.ui.main

interface MainView {
    fun showLoading()
    fun onResponse(jual: String, beli: String, time: String)
    fun onError(message: String?)
    fun hideLoading()
}