package nl.tcilegnar.weer9292.dagger.modules

import dagger.Binds
import dagger.Module
import nl.tcilegnar.weer9292.repo.CurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.ForecastRepo
import nl.tcilegnar.weer9292.repo.ICurrentWeatherRepo
import nl.tcilegnar.weer9292.repo.IForecastRepo
import javax.inject.Singleton

@Module
abstract class RepositoryBindings {
    @Binds
    @Singleton
    abstract fun bindsCurrentWeatherRepo(forecastRepo: CurrentWeatherRepo): ICurrentWeatherRepo

    @Binds
    @Singleton
    abstract fun bindsForecastRepo(forecastRepo: ForecastRepo): IForecastRepo
}
