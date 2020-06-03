package nl.tcilegnar.weer9292.model

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import nl.tcilegnar.weer9292.R
import kotlin.math.roundToInt

enum class TemperatureUnit(@StringRes val unitTextRes: Int, @DrawableRes val iconRes: Int) {
    CELCIUS(R.string.degreesCelciusUnit, R.drawable.ic_c),
    FAHRENHEIT(R.string.degreesFahrenheitUnit, R.drawable.ic_f);

    fun getUnitText(context: Context): String = context.getString(unitTextRes)

    fun getMenuIcon(context: Activity): Drawable? = ContextCompat.getDrawable(context, iconRes)

    fun getTemperatureText(context: Context, temperatureCelcius: Int): String {
        val temperatureValue = when (this) {
            CELCIUS -> temperatureCelcius
            FAHRENHEIT -> ((9.0 / 5.0) * temperatureCelcius + 32).roundToInt()
        }
        return "$temperatureValue${getUnitText(context)}"
    }
}
