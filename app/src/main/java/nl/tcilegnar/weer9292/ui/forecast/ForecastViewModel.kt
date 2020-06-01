package nl.tcilegnar.weer9292.ui.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.WeatherApi
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.network.model.response.*

/**
 * Easily turn on/off real API call vs. mock responses, to prevent hitting the limit of 100 calls a day on a free account:
 * https://rapidapi.com/community/api/open-weather-map/pricing
 */
private val useMockedData = BuildConfig.DEBUG
private const val TAG = "ForecastViewModel"

class ForecastViewModel(
    private val weatherService: WeatherServices = WeatherApi.getInstance().service
) : ViewModel() {
    private val coordinates = Coordinates.get9292HQ() // TODO (PK): read from repo, saved in HomeViewModel

    private val _forecast = MutableLiveData<DailyForecast?>().apply {
        value = null
    }

    val forecast: LiveData<DailyForecast?> = _forecast

    init {
        if (useMockedData) {
            _forecast.postValue(getMockedResponse())
        } else {
            getDailyForecast(coordinates)
        }
    }

    private fun getDailyForecast(
        coordinates: Coordinates
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherService.getDailyForecast(lat = coordinates.lat, lon = coordinates.lon)
                // TODO: improve response handling (check isSuccess, handle failed, convert to a useful model for on view side, etc)
                _forecast.postValue(response)
            } catch (e: Exception) {
                // TODO: improve user feedback on error
                Log.w(TAG, "Error on getCurrentWeather: ", e)
            }
        }
    }

    private fun getMockedResponse() = DailyForecast(
        listOf(
            getMockedDailyWeather(1590940089, 14.22, 18.33),
            getMockedDailyWeather(1590940089, 14.00, 18.00),
            getMockedDailyWeather(1590940089, 14.00, 18.00)
        ),
        City(
            2745912,
            "Utrecht",
            Coordinates.get9292HQ(),
            "NL"
        )
    )

    private fun getMockedDailyWeather(
        epoch: Long,
        minTemp: Double,
        maxTemp: Double
    ) = DailyWeather(
        epoch = epoch,
        sunriseEpoch = 1590940089,
        sunsetEpoch = 1590940089,
        temperatureProperties = TemperatureProperties(minTemp, maxTemp),
        pressure = 1023,
        humidity = 44,
        weatherTypes = listOf(WeatherType(803, "Clouds", "broken clouds")),
        windSpeed = 1.79,
        windDegrees = 60
    )
}
