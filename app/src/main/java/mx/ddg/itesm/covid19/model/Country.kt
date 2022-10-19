package mx.ddg.itesm.covid19.model

import com.google.gson.annotations.SerializedName

/**
 * @author David Damian
 * Class to represent one country.
 */
data class Country(
    @SerializedName("country")
    val name: String,
    @SerializedName("cases")
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("countryInfo")
    val info: Map<String, String> = mapOf(),
    val imageId: Int = 0,
    val imageUrl: String = "")
