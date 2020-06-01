package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.DailyWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.Location
import nl.tcilegnar.weer9292.network.model.response.toFullWeatherTypeDescription
import nl.tcilegnar.weer9292.util.EpochCalculator
import org.joda.time.DateTime

// Quick and simple weather details data class, which can contain all other details
data class WeatherDetails(
    val basicWeather: Weather,
    val weatherDescription: String,
    val pressure: Int,
    val humidity: Int,
    val sunriseEpoch: DateTime,
    val sunsetEpoch: DateTime
) {
    companion object {
        private val epochCalculator = EpochCalculator()

        fun from(response: CurrentWeatherResponse) = WeatherDetails(
            Weather.from(response),
            response.weatherTypes.toFullWeatherTypeDescription(),
            response.properties.pressure,
            response.properties.humidity,
            epochCalculator.epochToDateTime(response.sys.sunriseEpoch),
            epochCalculator.epochToDateTime(response.sys.sunsetEpoch)
        )

        fun from(response: DailyWeatherResponse, location: Location) = WeatherDetails(
            Weather.from(response, location),
            response.weatherTypes.toFullWeatherTypeDescription(),
            response.pressure,
            response.humidity,
            epochCalculator.epochToDateTime(response.sunriseEpoch),
            epochCalculator.epochToDateTime(response.sunsetEpoch)
        )
    }
}
