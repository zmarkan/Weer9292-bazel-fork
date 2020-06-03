package nl.tcilegnar.weer9292.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import kotlinx.android.synthetic.main.weather_detail.view.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.util.extensions.showIf

class WeatherDetail @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.weather_detail, this, true)
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.WeatherDetail)
            weather_detail_label.text = typedArray.getString(R.styleable.WeatherDetail_detail_label)
            typedArray.recycle()
        }
    }

    fun setValue(value: String?) {
        weather_detail_value.text = value
        showIf(value != null && value.isNotBlank())
    }

    fun setValue(value: Any?) {
        setValue(value?.toString())
    }

    fun setTemperature(temperature: Int?) {
        setValue(temperature?.let {
            TemperatureView.getTemperatureText(context, it)
        })
    }
}
