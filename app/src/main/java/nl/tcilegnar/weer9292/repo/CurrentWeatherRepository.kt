package nl.tcilegnar.weer9292.repo

import nl.tcilegnar.weer9292.model.WeatherDetails
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.util.Mocks

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepo private constructor(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service,
    mocks: Mocks = Mocks()
) : ApiCallRepo<CurrentWeatherResponse, WeatherDetails>(mocks) {
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

    fun getCurrentWeather(
        coordinates: Coordinates
    ) {
        startApiCall({
            weatherService.getCurrentWeather(lat = coordinates.lat, lon = coordinates.lon)
        }, processNetworkResponse = {
            WeatherDetails.from(it)
        }, handleError = {
            "Unable to retrieve current weather: something went wrong."
        }, mockData = {
            it.mockedCurrentWeatherResponse
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
        }, mockData = {
            it.mockedCurrentWeatherResponse
        })
    }
}
