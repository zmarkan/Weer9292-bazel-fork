package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("dt")
    val epoch: Long,
    @SerializedName("main")
    val properties: WeatherProperties,
    @SerializedName("weather")
    val weatherTypes: List<WeatherType>,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("coord")
    val coordinates: Coordinates,
    @SerializedName("sys")
    val sys: Sys
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
