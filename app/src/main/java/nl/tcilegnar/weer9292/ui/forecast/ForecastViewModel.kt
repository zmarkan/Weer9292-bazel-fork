package nl.tcilegnar.weer9292.ui.forecast

import androidx.lifecycle.ViewModel
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.ForecastRepository

class ForecastViewModel(
    forecastRepo: ForecastRepository = ForecastRepository.getInstance(),
    currentWeatherRepo: CurrentWeatherRepo = CurrentWeatherRepo.getInstance()
) : ViewModel() {
    val forecast = forecastRepo.dailyForecast

    init {
        currentWeatherRepo.weatherDetails.value?.let {
            forecastRepo.getDailyForecast(it.basicWeather.location.coordinates)
        }
    }
}
