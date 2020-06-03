package nl.tcilegnar.weer9292.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.temperature_view.view.*
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.storage.TemperaturePrefs

class TemperatureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.temperature_view, this, true)
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TemperatureView)
            temperature_label.text = typedArray.getString(R.styleable.TemperatureView_label)
            temperature_value.setTextColor(
                typedArray.getColor(
                    R.styleable.TemperatureView_text_color,
                    ContextCompat.getColor(context, R.color.defaultText)
                )
            )
            typedArray.recycle()
        }
    }

    fun setTemperature(context: Context, temperature: Int) {
        temperature_value.text = TemperaturePrefs(context).getTemperatureText(temperature)
    }
}
