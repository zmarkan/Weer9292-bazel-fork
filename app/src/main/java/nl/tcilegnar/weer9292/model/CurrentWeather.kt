package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.Wind
import org.joda.time.DateTime

data class CurrentWeather(
    val currentDateTime: DateTime,
    val location: Location,
    val weatherCondition: WeatherCondition,
    val temperatures: Temperatures,
    val wind: Wind,
    val pressure: Int,
    val humidity: Int
) {
    companion object {
        fun from(response: CurrentWeatherResponse) = CurrentWeather(
            DateTime(response.epoch),
            Location(response.city, response.sys.countryCode, response.coordinates),
            WeatherCondition.from(response.weatherTypes),
            Temperatures.from(response.properties),
            response.wind,
            response.properties.pressure,
            response.properties.humidity
        )
    }
}
