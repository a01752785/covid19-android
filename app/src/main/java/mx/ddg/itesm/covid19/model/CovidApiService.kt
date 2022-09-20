package mx.ddg.itesm.covid19.model

import retrofit2.Call
import retrofit2.http.GET

interface CovidApiService {
    @GET("v3/covid-19/countries")
    fun getAllCountryCovidData(): Call<List<Country>>
}