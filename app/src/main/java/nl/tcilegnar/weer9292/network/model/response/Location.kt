@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import nl.tcilegnar.weer9292.model.CityWithCountryCode

@Parcelize
data class Location(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("coord")
    val coordinates: Coordinates
) : Parcelable {
    fun getCityWithCountryCode() = CityWithCountryCode(cityName, countryCode)
}
