package nl.tcilegnar.weer9292.ui.home

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import nl.tcilegnar.weer9292.network.model.response.Coordinates
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.ForecastRepo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val currentWeatherRepo: CurrentWeatherRepo,
    private val forecastRepo: ForecastRepo
) : ViewModel() {
    val currentWeatherDetails = currentWeatherRepo.response
    val currentWeather = Transformations.map(currentWeatherDetails) {
        it?.basicWeather
    }
    val isLoading = currentWeatherRepo.isLoading
    val errorMessage = currentWeatherRepo.errorMessage

    fun getCurrentWeather(coordinates: Coordinates) {
        currentWeatherRepo.getCurrentWeather(coordinates)
    }

    fun getCurrentWeather(cityName: String) {
        currentWeatherRepo.getCurrentWeather(cityName)
    }

    fun testHome() {
        currentWeatherRepo.testHomeRepo()
        forecastRepo.testForecaseRepo()
    }
}
