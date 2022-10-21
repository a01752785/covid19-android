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
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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
        subscribeCovidTimeSeriesData()
        viewModel.getCountryCovidData(args.countryName)
        viewModel.getCountryTimeSeriesData(args.countryName, 935)
    }

    private fun subscribeCovidTimeSeriesData() {
        viewModel.countryTimeSeries.observe(viewLifecycleOwner) { countryTimeSeriesData ->
            val data = ArrayList<Entry>()
            var idx = 0
            countryTimeSeriesData.timeline["cases"]?.forEach { dayCases ->
                data.add(Entry(idx.toFloat(), dayCases.value.toFloat()))
                idx += 1
            }
            val lineDataSet = LineDataSet(data, "Contagios")
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)
            val lineData = LineData(dataSets)
            binding.lineChartView.data = lineData
            binding.lineChartView.invalidate()
            binding.lineChartView.description.text = "COVID-19"
        }
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