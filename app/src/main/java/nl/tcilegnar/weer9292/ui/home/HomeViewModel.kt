package nl.tcilegnar.weer9292.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.*

/**
 * Easily turn on/off real API call vs. mock responses, to prevent hitting the limit of 100 calls a day on a free account:
 * https://rapidapi.com/community/api/open-weather-map/pricing
 */
private val useMockedData = BuildConfig.DEBUG
private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather?>().apply {
        value = null
    }

    val currentWeather: LiveData<CurrentWeather?> = _currentWeather

    init {
        if (useMockedData) {
            _currentWeather.postValue(getMockedResponse())
        } else {
            getCurrentWeather()
        }
    }

    private fun getCurrentWeather(
        coordinates: Coordinates = Coordinates.get9292HQ()
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _currentWeather.postValue(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    private fun getMockedResponse() = CurrentWeather(
        1590940089,
        WeatherProperties(17.79, 14.86, 17.22, 18.33, 1023, 44),
        listOf(WeatherType(803, "Clouds", "broken clouds")),
        Wind(1.79, 60, 5.81),
        Coordinates.get9292HQ(),
        Sys(2004348, "NL", 1590895545, 1590954547)
    )
}
