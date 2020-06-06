package nl.tcilegnar.weer9292.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.model.DailyForecast
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse
import nl.tcilegnar.weer9292.network.util.Mocks

private const val TAG = "ForecastRepository"

class ForecastRepository private constructor(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service,
    private val mocks: Mocks = Mocks()
) : ApiCallRepo() {
    // Quick singleton implementation
    companion object {
        @Volatile
        private var INSTANCE: ForecastRepository? = null

        fun getInstance(): ForecastRepository {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = ForecastRepository()
                INSTANCE = instance
                return instance
            }
        }
    }

    private val _dailyForecast = MutableLiveData<DailyForecast?>().apply {
        value = null
    }

    val dailyForecast: LiveData<DailyForecast?> = _dailyForecast

    fun getDailyForecast(coordinates: Coordinates) {
        if (mocks.shouldUseMockedData) {
            updateResponse(mocks.mockedDailyForecastResponse)
            return
        }

        startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                updateResponse(response)
            } catch (e: Exception) {
                onError("Unable to retrieve forecast: something went wrong.", e)
            }
        }
    }

    private fun updateResponse(dailyForecastResponse: DailyForecastResponse) {
        stopLoading()
        _dailyForecast.postValue(DailyForecast.from(dailyForecastResponse))
    }
}
