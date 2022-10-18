package mx.ddg.itesm.covid19

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.ddg.itesm.covid19.databinding.ActivityMainBinding
import mx.ddg.itesm.covid19.databinding.FragmentCountryListBinding
import mx.ddg.itesm.covid19.model.Country
import mx.ddg.itesm.covid19.view.CountryAdapter
import mx.ddg.itesm.covid19.viewmodel.CountryListViewModel


class CountryListFragment : Fragment() {

    private lateinit var binding: FragmentCountryListBinding

    private val viewModel: CountryListViewModel by viewModels()

    private var countryAdapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservables()
        configureRecyclerView()
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
        val layout = LinearLayoutManager(requireContext())
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.rvCountries.layoutManager = layout
        countryAdapter = CountryAdapter(requireContext(), countries)
        countryAdapter?.setOnClickListener { p0 ->
            val tvCountryName: TextView? = p0?.findViewById(R.id.tvCountryName)
            if (tvCountryName != null) {
                val countryName = tvCountryName.text as String
                val action = CountryListFragmentDirections
                    .actionCountryListFragmentToCountryGraphFragment(countryName)
                findNavController().navigate(action)
            }
        }
        binding.rvCountries.adapter = countryAdapter

        val divider = DividerItemDecoration(requireContext(), layout.orientation)
        binding.rvCountries.addItemDecoration(divider)
    }
}