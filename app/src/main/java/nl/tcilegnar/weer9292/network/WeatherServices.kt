package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.network.model.CityWithCountryCode
import nl.tcilegnar.weer9292.network.model.response.CurrentWeather
import nl.tcilegnar.weer9292.network.model.response.DailyForecast
import nl.tcilegnar.weer9292.network.model.response.Forecast
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherServices {
    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = "metrics"
    ): CurrentWeather

    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metrics"
    ): CurrentWeather

    @Headers("Content-Type: application/json")
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = "metrics"
    ): Forecast

    @Headers("Content-Type: application/json")
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metrics"
    ): Forecast

    // Paid API calls:

    @Headers("Content-Type: application/json")
    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = "metrics"
    ): DailyForecast

    @Headers("Content-Type: application/json")
    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metrics"
    ): DailyForecast
}
