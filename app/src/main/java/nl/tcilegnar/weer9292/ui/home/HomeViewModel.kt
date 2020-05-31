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
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.CurrentWeather

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather?>().apply {
        value = null
    }

    val currentWeather: LiveData<CurrentWeather?> = _currentWeather

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather(
        coordinates: Coordinates = Coordinates.get9292HQ()
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _currentWeather.postValue(response)

                // TODO (PK): Testing API calls:
                // getForecast(response.coordinates)
                // getDailyForecast(coordinates)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    private fun getForecast(
        coordinates: Coordinates
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    private fun getDailyForecast(
        coordinates: Coordinates
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }
}
