package nl.tcilegnar.weer9292.ui.forecast

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
import nl.tcilegnar.weer9292.network.model.response.DailyForecast
import nl.tcilegnar.weer9292.network.util.Mocks

private const val TAG = "ForecastViewModel"

class ForecastViewModel(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service,
    mocks: Mocks = Mocks()
) : ViewModel() {
    private val coordinates = Coordinates.get9292HQ() // TODO (PK): read from repo, saved in HomeViewModel

    private val _forecast = MutableLiveData<DailyForecast?>().apply {
        value = null
    }

    val forecast: LiveData<DailyForecast?> = _forecast

    init {
        if (mocks.shouldUseMockedData) {
            _forecast.postValue(mocks.getMockedForecast())
        } else {
            getDailyForecast(coordinates)
        }
    }

    private fun getDailyForecast(
        coordinates: Coordinates
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _forecast.postValue(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }
}
