package nl.tcilegnar.weer9292.repo

import android.util.Log
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
) {
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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                updateResponse(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getDailyForecast: ", e)
            }
        }
    }

    private fun updateResponse(dailyForecastResponse: DailyForecastResponse) {
        _dailyForecast.postValue(DailyForecast.from(dailyForecastResponse))
    }
}
