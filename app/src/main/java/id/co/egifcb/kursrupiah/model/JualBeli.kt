package id.co.egifcb.kursrupiah.model

import com.google.gson.annotations.SerializedName

data class JualBeli (
        @SerializedName("Jual")
        val jual: String? = null,

        @SerializedName("Beli")
        val beli: String? = null)