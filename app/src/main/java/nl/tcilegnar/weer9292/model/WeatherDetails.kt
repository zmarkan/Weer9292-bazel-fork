package nl.tcilegnar.weer9292.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import nl.tcilegnar.weer9292.network.model.response.CurrentWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.DailyWeatherResponse
import nl.tcilegnar.weer9292.network.model.response.Location
import nl.tcilegnar.weer9292.network.model.response.toFullWeatherTypeDescription
import nl.tcilegnar.weer9292.util.EpochCalculator
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

// Quick and simple weather details data class, which can contain all other details
@Parcelize
data class WeatherDetails(
    val basicWeather: Weather,
    val weatherDescription: String,
    val pressure: Int,
    val humidity: Int,
    val sunriseDateTime: DateTime?,
    val sunsetDateTime: DateTime?
) : Parcelable {
    companion object {
        private val epochCalculator = EpochCalculator()

        fun from(response: CurrentWeatherResponse) = WeatherDetails(
            Weather.from(response),
            response.weatherTypes.toFullWeatherTypeDescription(),
            response.properties.pressure,
            response.properties.humidity,
            response.sys.sunriseEpoch?.let { epochCalculator.epochToDateTime(it) },
            response.sys.sunsetEpoch?.let { epochCalculator.epochToDateTime(it) }
        )

        fun from(response: DailyWeatherResponse, location: Location) = WeatherDetails(
            Weather.from(response, location),
            response.weatherTypes.toFullWeatherTypeDescription(),
            response.pressure,
            response.humidity,
            epochCalculator.epochToDateTime(response.sunriseEpoch),
            epochCalculator.epochToDateTime(response.sunsetEpoch)
        )
    }

    fun getSunriseTimeFormatted(): String? = sunriseDateTime?.let { DateTimeFormat.forPattern("H:mm").print(it) }

    fun getSunsetTimeFormatted(): String? = sunsetDateTime?.let { DateTimeFormat.forPattern("H:mm").print(it) }
}
