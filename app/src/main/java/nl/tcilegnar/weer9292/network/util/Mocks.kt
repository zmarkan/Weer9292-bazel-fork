package nl.tcilegnar.weer9292.network.util

import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.model.response.*

class Mocks(
    /**
     * Easily turn on/off real API call vs. mock responses, to prevent hitting the limit of 100 calls a day on a free account:
     * https://rapidapi.com/community/api/open-weather-map/pricing
     */
    val shouldUseMockedData: Boolean = BuildConfig.DEBUG
) {
    val mockedCurrentWeather = CurrentWeather(
        1590940089,
        WeatherProperties(17.79, 14.86, 17.22, 18.33, 1023, 44),
        listOf(WeatherType(803, "Clouds", "broken clouds")),
        Wind(1.79, 60, 5.81),
        Coordinates.get9292HQ(),
        Sys(2004348, "NL", 1590895545, 1590954547)
    )

    val mockedForecast = DailyForecast(
        listOf(
            getMockedDailyWeather(1591009200, 13.33, 21.68),
            getMockedDailyWeather(1591095600, 14.00, 18.00),
            getMockedDailyWeather(1591182000, 15.00, 19.00),
            getMockedDailyWeather(1591268400, 16.00, 20.00),
            getMockedDailyWeather(1591354800, 17.00, 21.00),
            getMockedDailyWeather(1591441200, 18.00, 22.00),
            getMockedDailyWeather(1591527600, 19.00, 23.00)
        ),
        City(
            2745912,
            "Utrecht",
            Coordinates.get9292HQ(),
            "NL"
        )
    )

    private fun getMockedDailyWeather(
        epoch: Long,
        minTemp: Double,
        maxTemp: Double
    ) = DailyWeather(
        epoch = epoch,
        sunriseEpoch = 1590940089,
        sunsetEpoch = 1590940089,
        temperatureProperties = TemperatureProperties(minTemp, maxTemp),
        pressure = 1023,
        humidity = 44,
        weatherTypes = listOf(WeatherType(803, "Clouds", "broken clouds")),
        windSpeed = 1.79,
        windDegrees = 60
    )
}
