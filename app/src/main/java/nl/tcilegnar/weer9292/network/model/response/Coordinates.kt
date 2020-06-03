@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
) : Parcelable {
    companion object {
        fun get9292HQ(): Coordinates = Coordinates(lat = 52.082491, lon = 5.117606)
    }
}
