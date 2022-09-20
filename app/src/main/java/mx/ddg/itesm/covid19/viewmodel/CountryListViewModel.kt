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

class CountryListViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://disease.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val covidApiService by lazy {
        retrofit.create(CovidApiService::class.java)
    }

    fun getAllCountryCovidData() {
        val call = covidApiService.getAllCountryCovidData()
        call.enqueue(object: Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                } else {
                    println("Error with data: {$response.body()}")

                }

            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                println("Not possible to get data")
            }
        })
    }
}