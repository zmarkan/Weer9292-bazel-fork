package nl.tcilegnar.weer9292.repo

import android.util.Log
import nl.tcilegnar.weer9292.model.DailyForecast
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse

interface ForecastRepository : IApiCallRepository<DailyForecast> {
    fun getDailyForecast(coordinates: Coordinates)
    fun testForecaseRepo()
}

class ForecastRepositoryImpl constructor(
    private val weatherService: WeatherServices
) : ApiCallRepo<DailyForecastResponse, DailyForecast>(), ForecastRepository {

    override fun getDailyForecast(coordinates: Coordinates) {
        startApiCall({
            weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
        }, processNetworkResponse = {
            DailyForecast.from(it)
        }, handleError = {
            "Unable to retrieve forecast: something went wrong."
        })
    }

    override fun testForecaseRepo() {
        Log.d("", "")
    }
}
