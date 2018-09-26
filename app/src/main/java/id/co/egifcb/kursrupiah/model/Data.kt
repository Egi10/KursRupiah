package id.co.egifcb.kursrupiah.model

import com.google.gson.annotations.SerializedName

data class Data (
        @SerializedName("Data")
        val data: MataUang?,

        @SerializedName("LastUpdate")
        val time: String?
)