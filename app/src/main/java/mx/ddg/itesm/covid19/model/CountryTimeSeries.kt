package mx.ddg.itesm.covid19.model

import com.google.gson.annotations.SerializedName

data class CountryTimeSeries(
    @SerializedName("country")
    val name: String,
    val timeline: Map<String, Map<String, String>> = mapOf(),
)
