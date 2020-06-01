package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.DailyWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.Location
import nl.tcilegnar.weer9292.network.model.response.Wind
import nl.tcilegnar.weer9292.util.EpochCalculator
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

data class Weather(
    val currentDateTime: DateTime,
    val location: Location,
    val weatherCondition: WeatherCondition,
    val temperatures: Temperatures,
    val wind: Wind
) {
    companion object {
        fun from(response: CurrentWeatherResponse) = Weather(
            EpochCalculator().epochToDateTime(response.epoch),
            Location(response.cityId, response.cityName, response.sys.countryCode, response.coordinates),
            WeatherCondition.from(response.weatherTypes),
            Temperatures.from(response.properties),
            response.wind
        )

        fun from(response: DailyWeatherResponse, location: Location) = Weather(
            EpochCalculator().epochToDateTime(response.epoch),
            location,
            WeatherCondition.from(response.weatherTypes),
            Temperatures.from(response.weatherProperties()),
            response.wind()
        )
    }

    fun getDateTimeFormatted(): String = DateTimeFormat.forPattern("dd-MMM HH:mm").print(currentDateTime)

    fun getDateFormatted(): String = DateTimeFormat.forPattern("dd-MMM").print(currentDateTime)

    fun getDayOfWeekFormatted(): String = DateTimeFormat.forPattern("EEE").print(currentDateTime)
}
