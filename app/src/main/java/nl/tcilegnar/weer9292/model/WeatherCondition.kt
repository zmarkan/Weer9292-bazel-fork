package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.WeatherType

enum class WeatherCondition {
    SUN,
    SUN_CLOUDS,
    CLOUDS,
    OTHERS;

    companion object {
        /**
         * TODO (PK): add other cases
         * See codes here: https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
         */
        fun from(weatherTypes: List<WeatherType>) = when {
            weatherTypes.any { it.id == 800L } ->
                SUN
            weatherTypes.any { it.id in 801L..803L } ->
                SUN_CLOUDS
            weatherTypes.any { it.id == 804L } ->
                CLOUDS
            else ->
                OTHERS
        }
    }
}
