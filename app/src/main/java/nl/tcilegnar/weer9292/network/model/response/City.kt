@file:Suppress("SpellCheckingInspection")

package nl.tcilegnar.weer9292.network.model.response

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String
)
