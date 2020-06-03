package nl.tcilegnar.weer9292.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import kotlinx.android.synthetic.main.forecast_column.view.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.model.WeatherDetails

class ForecastColumn @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.forecast_column, this, true)
    }

    fun setWeatherData(
        weatherDetails: WeatherDetails,
        onForecastColumnClicked: (weatherDetails: WeatherDetails) -> Unit
    ) {
        val weather = weatherDetails.basicWeather
        forecast_day_of_week.text = weather.getDayOfWeekFormatted()
        forecast_date.text = weather.getDateFormatted()
        weather_icon.setImageResource(weather.weatherCondition.getIconRes())
        forecast_temp_max.text = TemperatureView.getTemperatureText(context, weather.temperatures.temperatureMax)
        forecast_temp_min.text = TemperatureView.getTemperatureText(context, weather.temperatures.temperatureMin)

        setOnClickListener {
            onForecastColumnClicked(weatherDetails)
        }
    }
}
