package mx.ddg.itesm.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.ddg.itesm.covid19.model.Country
import mx.ddg.itesm.covid19.model.CovidApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryGraphViewModel : ViewModel() {
    val country = MutableLiveData<Country>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://disease.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val covidApiService by lazy {
        retrofit.create(CovidApiService::class.java)
    }

    fun getCountryCovidData(countryName: String) {
        val call = covidApiService.getCountryCovidData(countryName)
        call.enqueue(object: Callback<Country> {
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                if (response.isSuccessful) {
                    country.value = response.body()
                    println(country.value)
                } else {
                    println("Error with data: {$response.body()}")

                }

            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                println("Not possible to get data")
            }
        })
    }
}