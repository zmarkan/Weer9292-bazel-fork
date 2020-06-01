package nl.tcilegnar.weer9292.util

import org.joda.time.DateTimeZone
import org.junit.Assert.assertEquals
import org.junit.Test

class EpochCalculatorTest {

    @Test
    fun epochSecondsToDateTime() {
        val dateTime = EpochCalculator().epochToDateTime(0L, DateTimeZone.UTC)

        assertEquals(1970, dateTime.year)
        assertEquals(1, dateTime.monthOfYear)
        assertEquals(1, dateTime.dayOfMonth)
        assertEquals(0, dateTime.hourOfDay)
        assertEquals(0, dateTime.minuteOfHour)
        assertEquals(0, dateTime.secondOfMinute)
    }

    @Test
    fun epochSecondsToDateTime_timezoneAmsterdam() {
        val dateTime = EpochCalculator().epochToDateTime(0L, DateTimeZone.forID("Europe/Amsterdam"))

        assertEquals(1970, dateTime.year)
        assertEquals(1, dateTime.monthOfYear)
        assertEquals(1, dateTime.dayOfMonth)
        assertEquals(1, dateTime.hourOfDay) // GMT+1
        assertEquals(0, dateTime.minuteOfHour)
        assertEquals(0, dateTime.secondOfMinute)
    }

    @Test
    fun epochSecondsToDateTime_someDayIn2020() {
        val dateTime = EpochCalculator().epochToDateTime(1591010830L, DateTimeZone.UTC)

        assertEquals(2020, dateTime.year)
        assertEquals(6, dateTime.monthOfYear)
        assertEquals(1, dateTime.dayOfMonth)
        assertEquals(11, dateTime.hourOfDay)
        assertEquals(27, dateTime.minuteOfHour)
        assertEquals(10, dateTime.secondOfMinute)
    }

    @Test
    fun epochSecondsToDateTime_someDayIn2020TimezoneAmsterdam() {
        val dateTime = EpochCalculator().epochToDateTime(1591010830L, DateTimeZone.forID("Europe/Amsterdam"))

        assertEquals(2020, dateTime.year)
        assertEquals(6, dateTime.monthOfYear)
        assertEquals(1, dateTime.dayOfMonth)
        assertEquals(13, dateTime.hourOfDay) // GMT+2 (summer time)
        assertEquals(27, dateTime.minuteOfHour)
        assertEquals(10, dateTime.secondOfMinute)
    }
}
