package nl.tcilegnar.weer9292.model

data class CityWithCountryCode(
    val city: String,
    val countryCode: String
) {
    override fun toString() = "$city, $countryCode"
}
