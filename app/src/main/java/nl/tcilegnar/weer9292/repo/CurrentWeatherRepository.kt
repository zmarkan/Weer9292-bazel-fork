package nl.tcilegnar.weer9292.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.model.WeatherDetails
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
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

    private val _currentWeatherDetails = MutableLiveData<WeatherDetails?>().apply {
        value = null
    }

    val currentWeatherDetails: LiveData<WeatherDetails?> = _currentWeatherDetails

    fun getCurrentWeather(
        coordinates: Coordinates
    ) {
        if (mocks.shouldUseMockedData) {
            updateResponse(mocks.mockedCurrentWeatherResponse)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                updateResponse(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    fun getCurrentWeather(
        cityName: String
    ) {
        if (mocks.shouldUseMockedData) {
            updateResponse(mocks.mockedCurrentWeatherResponse)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeatherSearch(cityName)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                if (response.results.isNotEmpty()) {
                    updateResponse(response.results[0])
                } else {
                    // TODO (PK): show "no results" message, and don't updateResponse!
                }
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    private fun updateResponse(currentWeatherResponse: CurrentWeatherResponse) {
        _currentWeatherDetails.postValue(WeatherDetails.from(currentWeatherResponse))
    }
}
