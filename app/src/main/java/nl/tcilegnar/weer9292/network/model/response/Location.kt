@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName
import nl.tcilegnar.weer9292.model.CityWithCountryCode

data class Location(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("coord")
    val coordinates: Coordinates
) {
    val cityWithCountryCode = CityWithCountryCode(cityName, countryCode)
}
