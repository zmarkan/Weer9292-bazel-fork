@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("list")
    val weathers: List<Weather>,
    @SerializedName("city")
    val city: City
)
