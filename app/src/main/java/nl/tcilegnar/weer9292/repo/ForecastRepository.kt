package nl.tcilegnar.weer9292.repo

import nl.tcilegnar.weer9292.model.DailyForecast
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse

interface ForecastRepository : IApiCallRepository<DailyForecast> {
    fun getDailyForecast(coordinates: Coordinates)
}

class ForecastRepositoryImpl private constructor(
    private val weatherService: WeatherServices
) : ApiCallRepo<DailyForecastResponse, DailyForecast>(), ForecastRepository {
    // Quick singleton implementation
    companion object {
        @Volatile
        private var INSTANCE: ForecastRepositoryImpl? = null

        fun getInstance(
            weatherService: WeatherServices
        ): ForecastRepositoryImpl {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = ForecastRepositoryImpl(weatherService)
                INSTANCE = instance
                return instance
            }
        }
    }

    override fun getDailyForecast(coordinates: Coordinates) {
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
