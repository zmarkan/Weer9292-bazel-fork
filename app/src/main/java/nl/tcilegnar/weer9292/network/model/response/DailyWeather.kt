package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class DailyWeather(
    @SerializedName("dt")
    val epoch: Long,
    @SerializedName("sunrise")
    val sunriseEpoch: Long,
    @SerializedName("sunset")
    val sunsetEpoch: Long,
    @SerializedName("temp")
    val temperatureProperties: TemperatureProperties,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("weather")
    val weatherTypes: List<WeatherType>,
    @SerializedName("speed")
    val windSpeed: Double,
    @SerializedName("degrees")
    val windDegrees: Int
) {
    val weatherProperties = WeatherProperties(
        temperatureMin = temperatureProperties.min,
        temperatureMax = temperatureProperties.max,
        pressure = pressure,
        humidity = humidity
    )

    val wind = Wind(windSpeed, windDegrees)
}

data class TemperatureProperties(
    @SerializedName("min")
    val min: Double,
    @SerializedName("max")
    val max: Double
)
