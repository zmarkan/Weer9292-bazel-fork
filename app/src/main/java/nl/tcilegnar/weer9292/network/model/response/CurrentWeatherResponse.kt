package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName
import nl.tcilegnar.weer9292.network.WeatherServices

data class CurrentWeatherResponse(
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
    val sys: Sys,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("id")
    val cityId: Long
)

/** Several of these fields are null when calling [WeatherServices.getCurrentWeatherSearch] */
data class Sys(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("sunrise")
    val sunriseEpoch: Long?,
    @SerializedName("sunset")
    val sunsetEpoch: Long?
)
