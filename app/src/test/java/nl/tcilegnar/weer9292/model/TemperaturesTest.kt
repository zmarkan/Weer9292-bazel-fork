package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.WeatherProperties
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperaturesTest {
    @Test
    fun temperaturesFromWeatherProperties() {
        val weatherProperties = WeatherProperties(1.0, 2.0, 3.0, 4.0, 1000, 50)

        val temperatures = Temperatures.from(weatherProperties)

        assertEquals(Temperatures(1, 2, 3, 4), temperatures)
    }

    @Test
    fun temperaturesFromWeatherProperties_roundedDown() {
        val weatherProperties = WeatherProperties(1.4, 2.4, 3.4, 4.4, 1000, 50)

        val temperatures = Temperatures.from(weatherProperties)

        assertEquals(Temperatures(1, 2, 3, 4), temperatures)
    }

    @Test
    fun temperaturesFromWeatherProperties_roundedUp() {
        val weatherProperties = WeatherProperties(1.5, 2.5, 3.5, 4.5, 1000, 50)

        val temperatures = Temperatures.from(weatherProperties)

        assertEquals(Temperatures(2, 3, 4, 5), temperatures)
    }
}
