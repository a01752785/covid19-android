package mx.ddg.itesm.covid19.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.ddg.itesm.covid19.R
import mx.ddg.itesm.covid19.model.Country
import java.security.AccessController.getContext

/**
 * @author David Damian
 * The datasource for the RecyclerView
 */
class CountryAdapter(private val context: Context, var countries: Array<Country>)
    : RecyclerView.Adapter<CountryAdapter.CountryRow>() {

    // This function is called each time a row is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRow {
        val view = LayoutInflater.from(context).inflate(R.layout.country_row, parent, false)
        return CountryRow(view)
    }

    // To populate a row (put data in the 'position' row
    override fun onBindViewHolder(holder: CountryRow, position: Int) {
        val country = countries[position]
        holder.set(country)
    }

    // The number of rows in the RecyclerView
    override fun getItemCount(): Int {
        return countries.size
    }

    class CountryRow (var countryRow: View) : RecyclerView.ViewHolder(countryRow) {
        fun set(country: Country) {
            countryRow.findViewById<TextView>(R.id.tvCountryName).text = country.name
            countryRow.findViewById<TextView>(R.id.tvCases).text = "${country.cases}"
            val imgFlag = countryRow.findViewById<ImageView>(R.id.imgFlag)
            Glide.with(countryRow.context).load(country.info["flag"]).into(imgFlag)
        }
    }
}