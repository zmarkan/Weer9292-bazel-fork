package nl.tcilegnar.weer9292.repo

import android.util.Log
import nl.tcilegnar.weer9292.model.WeatherDetails
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherRepo @Inject constructor(
    private val weatherService: WeatherServices
) : ApiCallRepo<CurrentWeatherResponse, WeatherDetails>() {
    fun getCurrentWeather(
        coordinates: Coordinates
    ) {
        startApiCall({
            weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
        }, processNetworkResponse = {
            WeatherDetails.from(it)
        }, handleError = {
            "Unable to retrieve current weather: something went wrong."
        })
    }

    fun getCurrentWeather(
        cityName: String
    ) {
        startApiCall({
            val response = weatherService.getCurrentWeatherSearch(cityName)
            response.results.first()
        }, processNetworkResponse = {
            WeatherDetails.from(it)
        }, handleError = {
            if (it is NoSuchElementException) { // easy way to catch if there is no 'first' result. Could have used a custom exception for more reliability
                "No result found for $cityName"
            } else {
                "Unable to retrieve current weather for $cityName: something went wrong."
            }
        })
    }

    fun testHomeRepo() {
        Log.d("", "")
    }
}
