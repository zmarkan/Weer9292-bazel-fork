package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.*
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test

private const val CITY = "City"
private const val COUNTRY_CODE = "NL"
private val COORDINATES = Coordinates(12.0, 13.0)
private val WIND = Wind(6.0, 360, 7.0)

class CurrentWeatherTest {

    @Test
    fun currentWeatherFromResponse() {
        val response = CurrentWeatherResponse(
            0,
            WeatherProperties(1.0, 2.0, 3.0, 4.0, 1000, 50),
            listOf(WeatherType(803, "Clouds", "broken clouds")),
            WIND,
            COORDINATES,
            Sys(12345, COUNTRY_CODE, 1590895545, 1590954547),
            CITY
        )

        val currentWeather = CurrentWeather.from(response)

        val expectedCurrentWeather = CurrentWeather(
            DateTime(0),
            Location(CITY, COUNTRY_CODE, COORDINATES),
            WeatherCondition.SUN_CLOUDS,
            Temperatures(1, 2, 3, 4),
            WIND,
            1000,
            50
        )
        assertEquals(expectedCurrentWeather, currentWeather)
    }
}
