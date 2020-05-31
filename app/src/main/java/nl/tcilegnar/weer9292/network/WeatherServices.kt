package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.network.model.CityWithCountryCode
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherServices {
    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = "metrics"
    ): CurrentWeatherResponse
}
