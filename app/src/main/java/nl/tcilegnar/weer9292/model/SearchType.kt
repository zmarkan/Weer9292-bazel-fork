package nl.tcilegnar.weer9292.model

import java.util.*

enum class SearchType {
    ACCURATE,
    LIKE;

    override fun toString(): String {
        return name.toLowerCase(Locale.getDefault())
    }
}
