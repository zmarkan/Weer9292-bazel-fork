package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("dt")
    val epoch: Long,
    @SerializedName("main")
    val properties: WeatherProperties,
    @SerializedName("weather")
    val weatherTypes: List<WeatherType>,
    @SerializedName("wind")
    val wind: Wind
)

data class WeatherProperties(
    /** temperature can be null: not available for [DailyWeatherResponse] by default */
    @SerializedName("temp")
    val temperature: Double? = null,

    /** temperatureFeelsLike can be null: not available for [DailyWeatherResponse] by default */
    @SerializedName("feels_like")
    val temperatureFeelsLike: Double? = null,

    @SerializedName("temp_min")
    val temperatureMin: Double,
    @SerializedName("temp_max")
    val temperatureMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

data class WeatherType(
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)

fun List<WeatherType>.toFullWeatherTypeDescription() = joinToString(separator = ",\n") { it.description }

data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degrees: Int,

    /** Gust can be null: not available for [ForecastResponse] or [DailyForecastResponse] by default */
    @SerializedName("gust")
    val gust: Double? = null
)
