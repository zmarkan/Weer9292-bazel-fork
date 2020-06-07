package nl.tcilegnar.weer9292.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.dagger.factories.ViewModelFactory
import nl.tcilegnar.weer9292.model.Temperatures
import nl.tcilegnar.weer9292.model.Weather
import nl.tcilegnar.weer9292.storage.TemperaturePrefs
import nl.tcilegnar.weer9292.ui.home.HomeFragmentDirections.Companion.actionHomeFragmentToWeatherDetailsFragment
import nl.tcilegnar.weer9292.util.extensions.getViewModel

class HomeFragment(
    private val homeVMF: ViewModelFactory<HomeViewModel>
) : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var temperaturePrefs: TemperaturePrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = getViewModel(homeVMF, HomeViewModel::class.java)
        temperaturePrefs = TemperaturePrefs(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.currentWeatherDetails.observe(viewLifecycleOwner, Observer { currentWeatherDetails ->
            currentWeatherDetails?.let {
                home_weather_details.setOnClickListener {
                    findNavController().navigate(actionHomeFragmentToWeatherDetailsFragment(currentWeatherDetails))
                }
            }
        })
        homeViewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentWeather ->
            currentWeather?.let {
                setData(currentWeather)
            }
        })
    }

    private fun setData(weather: Weather) {
        home_date.text = weather.getDateTimeFormatted()
        weather_icon.setImageResource(weather.weatherCondition.getIconRes())
        temperaturePrefs.getTemperatureUnitLiveDataString().observe(viewLifecycleOwner, Observer {
            setTemperatures(weather.temperatures)
        })
    }

    private fun setTemperatures(temperatures: Temperatures) {
        val context = requireContext()
        temperatures.currentTemperature?.let {
            home_temp_now.text = TemperaturePrefs(context).getTemperatureText(it)
        }
        home_temp_max.setTemperature(context, temperatures.temperatureMax)
        home_temp_min.setTemperature(context, temperatures.temperatureMin)
    }
}
