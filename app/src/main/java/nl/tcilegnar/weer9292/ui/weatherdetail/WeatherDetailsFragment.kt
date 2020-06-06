package nl.tcilegnar.weer9292.ui.weatherdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_weather_details.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.model.Temperatures
import nl.tcilegnar.weer9292.model.WeatherDetails
import nl.tcilegnar.weer9292.storage.TemperaturePrefs

class WeatherDetailsFragment : Fragment() {
    private val args: WeatherDetailsFragmentArgs by navArgs()

    private lateinit var temperaturePrefs: TemperaturePrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        temperaturePrefs = TemperaturePrefs(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.weatherDetails!!) // Should always be set. If not: fail fast!
    }

    private fun setData(weatherDetails: WeatherDetails) {
        val weather = weatherDetails.basicWeather
        weather_detail_date.setValue(weather.getDateTimeFormatted())
        weather_detail_location.setValue(weather.location.getCityWithCountryCode())
        weather_detail_coordinates.setValue(weather.location.coordinates)
        weather_detail_description.setValue(weatherDetails.weatherDescription)
        weather_detail_wind_speed.setValue(weather.wind.speed)
        weather_detail_wind_angle.setValue(weather.wind.degrees)
        weather_detail_wind_gust.setValue(weather.wind.gust)
        weather_detail_pressure.setValue(weatherDetails.pressure)
        weather_detail_humidity.setValue(weatherDetails.humidity)
        weather_detail_sunrise.setValue(weatherDetails.getSunriseTimeFormatted())
        weather_detail_sunset.setValue(weatherDetails.getSunsetTimeFormatted())
        temperaturePrefs.getTemperatureUnitLiveDataString().observe(viewLifecycleOwner, Observer {
            setTemperatures(weather.temperatures)
        })
    }

    private fun setTemperatures(temperatures: Temperatures) {
        weather_detail_temp_current.setTemperature(temperatures.currentTemperature)
        weather_detail_temp_feel.setTemperature(temperatures.temperatureFeelsLike)
        weather_detail_temp_max.setTemperature(temperatures.temperatureMax)
        weather_detail_temp_min.setTemperature(temperatures.temperatureMin)
    }
}
