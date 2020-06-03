package nl.tcilegnar.weer9292.storage

import android.content.Context
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.storage.TemperaturePrefs.TemperatureUnit.CELCIUS
import nl.tcilegnar.weer9292.storage.TemperaturePrefs.TemperatureUnit.FAHRENHEIT

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

    fun getTemperatureText(temperature: Int): String {
        val unit = context.getString(
            when (getTemperatureUnit()) {
                CELCIUS -> R.string.degreesCelciusUnit
                FAHRENHEIT -> R.string.degreesFahrenheitUnit
            }
        )
        return "$temperature$unit"
    }

    enum class TemperatureUnit {
        CELCIUS,
        FAHRENHEIT
    }
}
