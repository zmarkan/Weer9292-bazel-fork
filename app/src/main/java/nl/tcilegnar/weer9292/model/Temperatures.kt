package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.WeatherProperties
import kotlin.math.roundToInt

data class Temperatures(
    val currentTemperature: Int? = null,
    val temperatureFeelsLike: Int? = null,
    val temperatureMin: Int,
    val temperatureMax: Int
) {
    companion object {
        fun from(properties: WeatherProperties) = Temperatures(
            properties.temperature?.roundToInt(),
            properties.temperatureFeelsLike?.roundToInt(),
            properties.temperatureMin.roundToInt(),
            properties.temperatureMax.roundToInt()
        )
    }
}
