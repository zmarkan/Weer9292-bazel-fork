package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.network.model.CityWithCountryCode
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherServices {
    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Header("x-rapidapi-host") host: String = "community-open-weather-map.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "27f4f00be9msh30ab00e9b5a2163p1856b8jsnf6218775be30",
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = "metrics"
    ): CurrentWeatherResponse
}
