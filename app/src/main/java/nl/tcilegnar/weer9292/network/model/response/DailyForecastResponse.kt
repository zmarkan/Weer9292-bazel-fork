@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    @SerializedName("list")
    val weatherResponses: List<DailyWeatherResponse>,
    @SerializedName("city")
    val location: Location
)
