package nl.tcilegnar.weer9292.model

import nl.tcilegnar.weer9292.network.model.response.Coordinates

data class Location(
    val city: String,
    val countryCode: String,
    val coordinates: Coordinates
)
