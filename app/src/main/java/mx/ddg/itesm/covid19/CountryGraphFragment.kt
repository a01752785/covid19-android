package mx.ddg.itesm.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mx.ddg.itesm.covid19.databinding.FragmentCountryGraphBinding

class CountryGraphFragment : Fragment() {

    private val args: CountryGraphFragmentArgs by navArgs()

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
        println(args.country)
    }
}