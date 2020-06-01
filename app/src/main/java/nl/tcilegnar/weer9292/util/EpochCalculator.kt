package nl.tcilegnar.weer9292.util

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.util.concurrent.TimeUnit

class EpochCalculator {
    /**
     * @param epoch the epoch value to convert to a [DateTime]. (note: epoch is a timestamp in seconds)
     * @param dateTimeZone  the timezone. Local timezone by default.
     * @return a [DateTime] instance with local timezone, unless otherwise specified
     */
    fun epochToDateTime(
        epoch: Long,
        dateTimeZone: DateTimeZone = DateTimeZone.getDefault()
    ): DateTime {
        return DateTime(TimeUnit.SECONDS.toMillis(epoch)).withZone(dateTimeZone)
    }
}
