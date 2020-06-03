package nl.tcilegnar.weer9292.storage

import android.content.Context
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.storage.TemperaturePrefs.TemperatureUnit.CELCIUS
import nl.tcilegnar.weer9292.storage.TemperaturePrefs.TemperatureUnit.FAHRENHEIT
import kotlin.math.roundToInt

class TemperaturePrefs(
    context: Context
) : SharedPrefs(context) {
    override fun prefsFileName() = "Temperature"

    companion object { // Prefs keys here
        private const val TEMPERATURE_UNIT = "TEMPERATURE_UNIT"
    }

    private fun getTemperatureUnit() = TemperatureUnit.valueOf(loadString(TEMPERATURE_UNIT, CELCIUS.name))

    private fun setTemperatureUnit(unit: TemperatureUnit) {
        return save(TEMPERATURE_UNIT, unit.name)
    }

    fun toggleTemperatureUnit() {
        setTemperatureUnit(
            when (getTemperatureUnit()) {
                CELCIUS -> FAHRENHEIT
                FAHRENHEIT -> CELCIUS
            }
        )
    }

    fun getTemperatureText(temperatureCelcius: Int): String {
        val temperatureUnit = getTemperatureUnit()
        val temperatureValue =
            when (temperatureUnit) {
                CELCIUS -> temperatureCelcius
                FAHRENHEIT -> ((9.0 / 5.0) * temperatureCelcius + 32).roundToInt()
            }
        val temperatureUnitText = context.getString(
            when (temperatureUnit) {
                CELCIUS -> R.string.degreesCelciusUnit
                FAHRENHEIT -> R.string.degreesFahrenheitUnit
            }
        )
        return "$temperatureValue$temperatureUnitText"
    }

    enum class TemperatureUnit {
        CELCIUS,
        FAHRENHEIT
    }
}
