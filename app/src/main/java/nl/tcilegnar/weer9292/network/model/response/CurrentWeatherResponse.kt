package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates,
    @SerializedName("weather")
    val basicWeather: List<BasicWeather>,
    @SerializedName("main")
    val mainWeather: MainWeather,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("dt")
    val epoch: Long,
    @SerializedName("sys")
    val sys: Sys
)

data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class BasicWeather(
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)

data class MainWeather(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val temperatureFeelsLike: Double,
    @SerializedName("temp_min")
    val temperatureMin: Double,
    @SerializedName("temp_max")
    val temperatureMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degrees: Int,
    @SerializedName("gust")
    val gust: Double
)

data class Sys(
    @SerializedName("id")
    val id: Long,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("sunrise")
    val sunriseEpoch: Long,
    @SerializedName("sunset")
    val sunsetEpoch: Long
)
