package nl.tcilegnar.weer9292.dagger.modules

import dagger.Module
import dagger.Provides
import nl.tcilegnar.weer9292.network.WeatherServices
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepoImpl
import nl.tcilegnar.weer9292.repo.ForecastRepository
import nl.tcilegnar.weer9292.repo.ForecastRepositoryImpl

@Module
object RepositoriesModule {
    @JvmStatic
    @Provides
    fun providesCurrentWeatherRepo(
        weatherService: WeatherServices
    ): CurrentWeatherRepo =
        CurrentWeatherRepoImpl.getInstance(weatherService)

    @JvmStatic
    @Provides
    fun providesForecastRepo(
        weatherService: WeatherServices
    ): ForecastRepository =
        ForecastRepositoryImpl(weatherService)
}
