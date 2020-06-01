package nl.tcilegnar.weer9292.ui.forecast

import androidx.lifecycle.ViewModel
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.repo.ForecastRepository

class ForecastViewModel(
    forecastRepo: ForecastRepository = ForecastRepository.getInstance()
) : ViewModel() {
    private val coordinates = Coordinates.get9292HQ() // TODO (PK): read from repo, saved in HomeViewModel

    val forecast = forecastRepo.forecast

    init {
        forecastRepo.getDailyForecast(coordinates)
    }
}
