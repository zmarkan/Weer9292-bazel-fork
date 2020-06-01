package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.model.WeatherCondition.*
import nl.tcilegnar.weer9292.network.model.response.WeatherType
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherConditionTest {
    @Test
    fun weatherConditionFromWeatherType_800() {
        val weatherTypes = listOf(WeatherType(800, "Clear", "clear sky"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(SUN, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_801() {
        val weatherTypes = listOf(WeatherType(801, "Clouds", "few clouds"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(SUN_CLOUDS, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_802() {
        val weatherTypes = listOf(WeatherType(802, "Clouds", "scattered clouds"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(SUN_CLOUDS, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_803() {
        val weatherTypes = listOf(WeatherType(803, "Clouds", "broken clouds"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(SUN_CLOUDS, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_804() {
        val weatherTypes = listOf(WeatherType(804, "Clouds", "overcast clouds"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(CLOUDS, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_200() {
        val weatherTypes = listOf(WeatherType(200, "Thunderstorm", "thunderstorm with light rain"))

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(OTHERS, weatherCondition)
    }

    @Test
    fun weatherConditionFromWeatherType_804_200() {
        val weatherTypes = listOf(
            WeatherType(804, "Clouds", "overcast clouds"),
            WeatherType(200, "Thunderstorm", "thunderstorm with light rain")
        )

        val weatherCondition = WeatherCondition.from(weatherTypes)

        assertEquals(CLOUDS, weatherCondition)
    }
}
