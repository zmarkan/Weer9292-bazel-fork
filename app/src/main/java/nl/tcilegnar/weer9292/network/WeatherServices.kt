package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.model.CityWithCountryCode
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse
import nl.tcilegnar.weer9292.network.model.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val DEFAULT_UNIT = "metric"

interface WeatherServices {
    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = DEFAULT_UNIT
    ): CurrentWeatherResponse

    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = DEFAULT_UNIT
    ): CurrentWeatherResponse

    @Headers("Content-Type: application/json")
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = DEFAULT_UNIT
    ): ForecastResponse

    @Headers("Content-Type: application/json")
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = DEFAULT_UNIT
    ): ForecastResponse

    // Paid API calls:

    @Headers("Content-Type: application/json")
    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("q") cityWithCountryCode: CityWithCountryCode,
        @Query("units") units: String = DEFAULT_UNIT
    ): DailyForecastResponse

    @Headers("Content-Type: application/json")
    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = DEFAULT_UNIT
    ): DailyForecastResponse
}
