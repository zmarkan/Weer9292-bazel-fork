package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.DailyWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.Location
import nl.tcilegnar.weer9292.network.model.response.Wind
import nl.tcilegnar.weer9292.util.EpochCalculator
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
            EpochCalculator().epochToDateTime(response.epoch),
            Location(response.cityId, response.cityName, response.sys.countryCode, response.coordinates),
            WeatherCondition.from(response.weatherTypes),
            Temperatures.from(response.properties),
            response.wind,
            response.properties.pressure,
            response.properties.humidity
        )

        fun from(response: DailyWeatherResponse, location: Location) = CurrentWeather(
            EpochCalculator().epochToDateTime(response.epoch),
            location,
            WeatherCondition.from(response.weatherTypes),
            Temperatures.from(response.weatherProperties),
            response.wind,
            response.pressure,
            response.humidity
        )
    }
}
