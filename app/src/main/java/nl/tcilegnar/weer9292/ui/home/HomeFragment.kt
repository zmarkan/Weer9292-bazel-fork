package nl.tcilegnar.weer9292.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.model.Temperatures
import nl.tcilegnar.weer9292.model.Weather
import nl.tcilegnar.weer9292.ui.customview.TemperatureView

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentWeather ->
            currentWeather?.let {
                setData(currentWeather)
                home_location.setOnClickListener {
                    // TODO (PK): show coordinates
                }
                home_weather_details.setOnClickListener {
                    // TODO (PK): start WeatherDetailsFragment
                }
            }
        })
        homeViewModel.currentCoordinates.observe(viewLifecycleOwner, Observer { currentCoordinates ->
            currentCoordinates?.let {
                Log.d("TEST", "currentCoordinates: $currentCoordinates")
            }
        })
    }

    private fun setData(weather: Weather) {
        home_location.text = weather.location.cityWithCountryCode.toString()
        home_date.text = weather.getDateTimeFormatted()
        weather_icon.setImageResource(weather.weatherCondition.getIconRes())
        setTemperatures(weather.temperatures)

        // TODO (PK): show coordinates?
    }

    private fun setTemperatures(temperatures: Temperatures) {
        val context = requireContext()
        temperatures.currentTemperature?.let {
            home_temp_now.text = TemperatureView.getTemperatureText(context, it)
        }
        home_temp_max.setTemperature(context, temperatures.temperatureMax)
        home_temp_min.setTemperature(context, temperatures.temperatureMin)
    }
}
