package nl.tcilegnar.weer9292.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_forecast.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.model.WeatherDetails
import nl.tcilegnar.weer9292.storage.TemperaturePrefs
import nl.tcilegnar.weer9292.ui.customview.ForecastColumn

class ForecastFragment : Fragment() {
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var temperaturePrefs: TemperaturePrefs

    private var columns = arrayListOf<ForecastColumn>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        temperaturePrefs = TemperaturePrefs(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        columns.clear()
        columns.addAll(columnContainer.children.map { it as ForecastColumn })
        forecastViewModel.forecast.observe(viewLifecycleOwner, Observer { dailyForecast ->
            dailyForecast?.let {
                setColumnData(it.weathers)
            }
        })
    }

    private fun setColumnData(dailyWeatherDetails: List<WeatherDetails>) {
        dailyWeatherDetails.take(columns.size).forEachIndexed { index, weatherDetails ->
            temperaturePrefs.getTemperatureUnitLiveDataString().observe(viewLifecycleOwner, Observer {
                setColumnData(index, weatherDetails)
            })
        }
    }

    private fun setColumnData(index: Int, weatherDetails: WeatherDetails) {
        columns[index].setWeatherData(weatherDetails) {
            findNavController().navigate(
                ForecastFragmentDirections.actionForecastFragmentToWeatherDetailsFragment(weatherDetails)
            )
        }
    }
}
