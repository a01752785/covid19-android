package mx.ddg.itesm.covid19

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.anychart.anychart.AnyChart
import com.anychart.anychart.AnyChartView
import com.anychart.anychart.DataEntry
import com.anychart.anychart.ValueDataEntry
import com.anychart.anychart.Pie
import com.bumptech.glide.Glide
import mx.ddg.itesm.covid19.databinding.FragmentCountryGraphBinding
import mx.ddg.itesm.covid19.viewmodel.CountryGraphViewModel


class CountryGraphFragment : Fragment() {

    private val args: CountryGraphFragmentArgs by navArgs()
    private val viewModel: CountryGraphViewModel by viewModels()
    private lateinit var binding: FragmentCountryGraphBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryGraphBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        println(args.countryName)
        subscribeCovidCountryData()
        viewModel.getCountryCovidData(args.countryName)
        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("John", 10000))
        data.add(ValueDataEntry("Jake", 12000))
        data.add(ValueDataEntry("Peter", 18000))
        for (i in 0 until data.size) {
            println(data[i].toString())
        }
        pie.setData(data)
        binding.anyChartView.setChart(pie)
        println("chart")
    }

    private fun subscribeCovidCountryData() {
        viewModel.country.observe(viewLifecycleOwner) { countryData ->
            binding.tvCurrentCountryName.text = countryData.name
            val imgFlag = view?.findViewById<ImageView>(mx.ddg.itesm.covid19.R.id.imgCurrentFlag)
            if (imgFlag != null) {
                Glide.with(requireContext()).load(countryData.info["flag"]).into(imgFlag)
            }
            binding.tvCurrentCountryCases.text = countryData.cases.toString()
            binding.tvCurrentCountryRecovered.text = countryData.recovered.toString()
            binding.tvCurrentCountryDead.text = countryData.deaths.toString()
        }
    }
}