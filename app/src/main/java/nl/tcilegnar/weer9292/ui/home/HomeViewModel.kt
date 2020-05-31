package nl.tcilegnar.weer9292.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.CityWithCountryCode
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponse?>().apply {
        value = null
    }

    val currentWeather: LiveData<CurrentWeatherResponse?> = _currentWeather

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather(
        getWeatherFor: CityWithCountryCode = CityWithCountryCode("utrecht", "nl")
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeather(cityWithCountryCode = getWeatherFor)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _currentWeather.postValue(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }
}
