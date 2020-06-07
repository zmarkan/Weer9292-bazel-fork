package nl.tcilegnar.weer9292.network.model

import nl.tcilegnar.weer9292.network.model.response.*

class Mocks {
    val mockedCurrentWeatherResponse = CurrentWeatherResponse(
        1590940089,
        WeatherProperties(17.79, 14.86, 17.22, 18.33, 1023, 44),
        listOf(WeatherType(803, "Clouds", "broken clouds")),
        Wind(1.79, 60, 5.81),
        Coordinates.get9292HQ(),
        Sys(2004348, "NL", 1590895545, 1590954547),
        "Mock Utrecht",
        2745912
    )

    val mockedCurrentWeatherSearchResponse = CurrentWeatherSearchResponse(
        listOf(
            // First result is Tokyo (mock data is nonesense, but we can differentiate it from Utrecht from mockedCurrentWeatherResponse)
            CurrentWeatherResponse(
                1590940089,
                WeatherProperties(27.79, 22.86, 21.22, 29.33, 1023, 44),
                listOf(WeatherType(800, "Clear", "clear sky")),
                Wind(1.79, 60, 5.81),
                Coordinates.get9292HQ(), // whatever!
                Sys(2004348, "JP", 1590895545, 1590954547),
                "Mock Tokyo",
                2745912
            ),
            mockedCurrentWeatherResponse
        )
    )

    val mockedDailyForecastResponse = DailyForecastResponse(
        listOf(
            getMockedDailyWeatherResponse(1591009200, 13.33, 21.68),
            getMockedDailyWeatherResponse(1591095600, 14.00, 18.00),
            getMockedDailyWeatherResponse(1591182000, 15.00, 19.00),
            getMockedDailyWeatherResponse(1591268400, 16.00, 20.00),
            getMockedDailyWeatherResponse(1591354800, 17.00, 21.00),
            getMockedDailyWeatherResponse(1591441200, 18.00, 22.00),
            getMockedDailyWeatherResponse(1591527600, 19.00, 23.00)
        ),
        Location(
            2745912,
            "Utrecht",
            "NL",
            Coordinates.get9292HQ()
        )
    )

    private fun getMockedDailyWeatherResponse(
        epoch: Long,
        minTemp: Double,
        maxTemp: Double
    ) = DailyWeatherResponse(
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
