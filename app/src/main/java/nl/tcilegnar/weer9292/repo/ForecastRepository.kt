package nl.tcilegnar.weer9292.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.DailyForecast
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

    private val _forecast = MutableLiveData<DailyForecast?>().apply {
        value = null
    }

    val forecast: LiveData<DailyForecast?> = _forecast

    fun getDailyForecast(
        coordinates: Coordinates
    ) {
        if (mocks.shouldUseMockedData) {
            _forecast.postValue(mocks.mockedForecast)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _forecast.postValue(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getDailyForecast: ", e)
            }
        }
    }
}
