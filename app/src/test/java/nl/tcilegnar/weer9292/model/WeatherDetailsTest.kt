package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.*
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test

private const val CITY = "City"
private const val COUNTRY_CODE = "NL"
private val COORDINATES = Coordinates(12.0, 13.0)
private val WIND = Wind(6.0, 360, 7.0)
private const val PRESSURE = 1000
private const val HUMIDITY = 50
private const val CITY_ID = 123L
private const val SUNRISE_EPOCH = 1590895545L
private const val SUNSET_EPOCH = 1590954547L

class WeatherDetailsTest {

    @Test
    fun weatherDetailsFromCurrentWeatherResponse() {
        val response = CurrentWeatherResponse(
            0,
            WeatherProperties(1.0, 2.0, 3.0, 4.0, PRESSURE, HUMIDITY),
            listOf(
                WeatherType(803, "Clouds", "broken clouds"),
                WeatherType(800, "Sunny", "clear skies")
            ),
            WIND,
            COORDINATES,
            Sys(12345, COUNTRY_CODE, SUNRISE_EPOCH, SUNSET_EPOCH),
            CITY,
            CITY_ID
        )

        val weatherDetails = WeatherDetails.from(response)

        val expectedWeatherDetails = WeatherDetails(
            Weather(
                DateTime(0),
                Location(CITY_ID, CITY, COUNTRY_CODE, COORDINATES),
                WeatherCondition.SUN,
                Temperatures(1, 2, 3, 4),
                WIND
            ),
            "broken clouds,\nclear skies",
            PRESSURE,
            HUMIDITY,
            SUNRISE_EPOCH,
            SUNSET_EPOCH
        )
        assertEquals(expectedWeatherDetails, weatherDetails)
    }

    @Test
    fun weatherDetailsFromDailyWeatherResponseAndLocation() {
        val response = DailyWeatherResponse(
            0,
            SUNRISE_EPOCH,
            SUNSET_EPOCH,
            TemperatureProperties(1.0, 2.0),
            PRESSURE,
            HUMIDITY,
            listOf(
                WeatherType(803, "Clouds", "broken clouds"),
                WeatherType(800, "Sunny", "clear skies")
            ),
            WIND.speed,
            WIND.degrees
        )

        val location = Location(CITY_ID, CITY, COUNTRY_CODE, COORDINATES)

        val weatherDetails = WeatherDetails.from(response, location)

        val expectedWeatherDetails = WeatherDetails(
            Weather(
                DateTime(0),
                location,
                WeatherCondition.SUN,
                Temperatures(null, null, 1, 2),
                Wind(WIND.speed, WIND.degrees, null)
            ),
            "broken clouds,\nclear skies",
            PRESSURE,
            HUMIDITY,
            SUNRISE_EPOCH,
            SUNSET_EPOCH
        )
        assertEquals(expectedWeatherDetails, weatherDetails)
    }
}
