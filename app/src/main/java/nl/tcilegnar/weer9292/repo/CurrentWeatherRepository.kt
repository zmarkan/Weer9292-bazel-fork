package nl.tcilegnar.weer9292.repo

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
) : ApiCallRepo() {
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

    private val _currentWeatherDetails = MutableLiveData<WeatherDetails?>(null)
    val currentWeatherDetails: LiveData<WeatherDetails?> = _currentWeatherDetails

    fun getCurrentWeather(
        coordinates: Coordinates
    ) {
        if (mocks.shouldUseMockedData) {
            updateResponse(mocks.mockedCurrentWeatherResponse)
            return
        }

        startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
                updateResponse(response)
            } catch (e: Exception) {
                onError("Unable to retrieve current weather: something went wrong.", e)
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

        startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getCurrentWeatherSearch(cityName)
                if (response.results.isNotEmpty()) {
                    updateResponse(response.results[0])
                } else {
                    onError("No result found for $cityName")
                }
            } catch (e: Exception) {
                onError("Unable to retrieve current weather for $cityName: something went wrong.", e)
            }
        }
    }

    private fun updateResponse(currentWeatherResponse: CurrentWeatherResponse) {
        stopLoading()
        _currentWeatherDetails.postValue(WeatherDetails.from(currentWeatherResponse))
    }
}
