package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.DailyForecastResponse
import nl.tcilegnar.weer9292.network.model.response.Location

data class DailyForecast(
    val weathers: List<WeatherDetails>,
    val location: Location
) {
    companion object {
        fun from(response: DailyForecastResponse) = DailyForecast(
            response.weatherResponses.map { WeatherDetails.from(it, response.location) },
            response.location
        )
    }
}
