package id.co.egifcb.kursrupiah.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import id.co.egifcb.kursrupiah.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {
    private lateinit var mainPresenter: MainPresenter
    private var usd: Int = 0
    private var byk_usd: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(this)
        mainPresenter = MainPresenter(this)
        mainPresenter.getKurs()

        btn_cek.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_cek -> {
                val jual = tv_jual.text.toString()
                val banyakUsd = et_usd.text.toString()

                usd = jual.toInt()
                byk_usd = banyakUsd.toInt()

                val hasil = byk_usd * usd

                tv_idr.text = hasil.toString()
            }
        }
    }

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
        ll_show.visibility = View.GONE
    }

    override fun onResponse(jual: String, beli: String, time: String) {
        tv_jual.text = jual
        changeDate(time)
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        pb_loading.visibility = View.GONE
        ll_show.visibility = View.VISIBLE
    }

    private fun changeDate(date: String?) {
        val input = "yyyy-MM-dd hh:mm:ss"
        val output = "EEEE, d MMMM yyyy"

        val inputFormat = SimpleDateFormat(input, Locale.getDefault())
        val outputFormat = SimpleDateFormat(output, Locale.getDefault())

        val dat = inputFormat.parse(date)
        val str = outputFormat.format(dat)

        tv_time.text = str
    }
}
