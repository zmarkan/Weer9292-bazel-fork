package nl.tcilegnar.weer9292.storage

import androidx.annotation.StringRes
import nl.tcilegnar.weer9292.BaseInstrumentedTest
import nl.tcilegnar.weer9292.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TemperaturePrefsTest : BaseInstrumentedTest() {
    private lateinit var temperaturePrefs: TemperaturePrefs

    @Before
    fun setUp() {
        temperaturePrefs = TemperaturePrefs(getContext())
    }

    @After
    fun tearDown() {
        temperaturePrefs.deletePreferences()
    }

    @Test
    fun getTemperatureText_0C() {
        val celcius = 0

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertCelcius(celcius, temperatureText)
    }

    @Test
    fun getTemperatureText_100C() {
        val celcius = 100

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertCelcius(celcius, temperatureText)
    }

    @Test
    fun getTemperatureText_minus40C() {
        val celcius = -40

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertCelcius(celcius, temperatureText)
    }

    @Test
    fun getTemperatureText_0CtoF() {
        temperaturePrefs.toggleTemperatureUnit()
        val celcius = 0

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertFahrenheit(32, temperatureText)
    }

    @Test
    fun getTemperatureText_100CtoF() {
        temperaturePrefs.toggleTemperatureUnit()
        val celcius = 100

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertFahrenheit(212, temperatureText)
    }

    @Test
    fun getTemperatureText_minus40CtoF() {
        temperaturePrefs.toggleTemperatureUnit()
        val celcius = -40

        val temperatureText = temperaturePrefs.getTemperatureText(celcius)

        assertFahrenheit(-40, temperatureText)
    }

    private fun assertCelcius(expectedValue: Int, actualText: String) {
        assertTemperature(expectedValue, R.string.degreesCelciusUnit, actualText)
    }

    private fun assertFahrenheit(expectedValue: Int, actualText: String) {
        assertTemperature(expectedValue, R.string.degreesFahrenheitUnit, actualText)
    }

    private fun assertTemperature(expectedValue: Int, @StringRes res: Int, actualText: String) {
        val expectedText = "$expectedValue${getString(res)}"
        assertEquals(expectedText, actualText)
    }
}
