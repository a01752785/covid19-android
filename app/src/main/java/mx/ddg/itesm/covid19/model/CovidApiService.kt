package mx.ddg.itesm.covid19.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApiService {
    @GET("v3/covid-19/countries")
    fun getAllCountryCovidData(): Call<List<Country>>

    @GET("v3/covid-19/countries/{country}")
    fun getCountryCovidData(@Path("country") countryName: String): Call<Country>
}