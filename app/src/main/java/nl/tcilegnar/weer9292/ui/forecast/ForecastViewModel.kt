package nl.tcilegnar.weer9292.ui.forecast

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import nl.tcilegnar.weer9292.model.DailyForecast
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.ForecastRepository

class ForecastViewModel(
    forecastRepo: ForecastRepository = ForecastRepository.getInstance(),
    currentWeatherRepo: CurrentWeatherRepo = CurrentWeatherRepo.getInstance()
) : ViewModel() {
    val forecast = Transformations.map(forecastRepo.forecastResponse) {
        it?.let { DailyForecast.from(it) }
    }

    init {
        currentWeatherRepo.currentCoordinates.value?.let {
            forecastRepo.getDailyForecast(it)
        }
    }
}
