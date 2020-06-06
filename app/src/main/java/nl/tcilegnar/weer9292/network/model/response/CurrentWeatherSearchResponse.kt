package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherSearchResponse(
    @SerializedName("list")
    val results: List<CurrentWeatherResponse>
)
