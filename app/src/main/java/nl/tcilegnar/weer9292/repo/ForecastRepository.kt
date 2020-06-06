package nl.tcilegnar.weer9292.repo

import nl.tcilegnar.weer9292.model.DailyForecast
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse
import nl.tcilegnar.weer9292.network.util.Mocks

class ForecastRepository private constructor(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service,
    mocks: Mocks = Mocks()
) : ApiCallRepo<DailyForecastResponse, DailyForecast>(mocks) {
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

    fun getDailyForecast(coordinates: Coordinates) {
        startApiCall({
            weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
        }, processNetworkResponse = {
            DailyForecast.from(it)
        }, handleError = {
            "Unable to retrieve forecast: something went wrong."
        }, mockData = {
            it.mockedDailyForecastResponse
        })
    }
}
