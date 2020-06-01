package nl.tcilegnar.weer9292.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.CurrentWeather
import nl.tcilegnar.weer9292.network.util.Mocks

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepo private constructor(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service,
    private val mocks: Mocks = Mocks()
) {
    // Quick singleton implementation
    companion object {
        @Volatile
        private var INSTANCE: CurrentWeatherRepo? = null

        fun getInstance(): CurrentWeatherRepo {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = CurrentWeatherRepo()
                INSTANCE = instance
                return instance
            }
        }
    }

    private val _currentWeather = MutableLiveData<CurrentWeather?>().apply {
        value = null
    }

    val currentWeather: LiveData<CurrentWeather?> = _currentWeather
    val currentCoordinates: LiveData<Coordinates?> = Transformations.map(currentWeather) { it?.coordinates }

    fun getCurrentWeather(
        coordinates: Coordinates
    ) {
        if (mocks.shouldUseMockedData) {
            _currentWeather.postValue(mocks.mockedCurrentWeather)
            return
        }

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
}
