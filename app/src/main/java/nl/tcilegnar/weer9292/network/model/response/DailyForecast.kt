@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("list")
    val weathers: List<DailyWeather>,
    @SerializedName("city")
    val city: City
)
