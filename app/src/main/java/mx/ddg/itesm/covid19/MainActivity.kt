package mx.ddg.itesm.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.ddg.itesm.covid19.databinding.ActivityMainBinding
import mx.ddg.itesm.covid19.model.Country
import mx.ddg.itesm.covid19.view.CountryAdapter
import mx.ddg.itesm.covid19.viewmodel.CountryListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: CountryListViewModel by viewModels()

    var countryAdapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservables()
        configureRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllCountryCovidData()
    }

    private fun setObservables() {
        viewModel.countries.observe(this) { countryList ->
            countryAdapter?.countries = countryList.toTypedArray()
            countryAdapter?.notifyDataSetChanged()
        }
    }

    private fun configureRecyclerView() {
        val countries = arrayOf(Country("Mexico", 1))
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.rvCountries.layoutManager = layout
        countryAdapter = CountryAdapter(this, countries)
        binding.rvCountries.adapter = countryAdapter

        val divider = DividerItemDecoration(this, layout.orientation)
        binding.rvCountries.addItemDecoration(divider)
    }
}