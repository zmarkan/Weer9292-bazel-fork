package nl.tcilegnar.weer9292.storage

import android.content.Context
import nl.tcilegnar.weer9292.model.TemperatureUnit
import nl.tcilegnar.weer9292.model.TemperatureUnit.CELCIUS
import nl.tcilegnar.weer9292.model.TemperatureUnit.FAHRENHEIT

class TemperaturePrefs(
    context: Context
) : SharedPrefs(context) {
    override fun prefsFileName() = "Temperature"

    companion object { // Prefs keys here
        private const val TEMPERATURE_UNIT = "TEMPERATURE_UNIT"
    }

    fun getTemperatureUnit() = TemperatureUnit.valueOf(loadString(TEMPERATURE_UNIT, CELCIUS.name))

    fun setTemperatureUnit(unit: TemperatureUnit) {
        save(TEMPERATURE_UNIT, unit.name)
    }

    fun toggleTemperatureUnit(): TemperatureUnit {
        val newUnit = when (getTemperatureUnit()) {
            CELCIUS -> FAHRENHEIT
            FAHRENHEIT -> CELCIUS
        }
        setTemperatureUnit(newUnit)
        return newUnit
    }

    fun getTemperatureText(temperatureCelcius: Int): String {
        return getTemperatureUnit().getTemperatureText(context, temperatureCelcius)
    }
}
