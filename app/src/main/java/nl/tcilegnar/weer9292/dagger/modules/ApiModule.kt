package nl.tcilegnar.weer9292.dagger.modules

import dagger.Module
import dagger.Provides
import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.dagger.modules.helpers.NetworkHelper
import nl.tcilegnar.weer9292.network.WeatherServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ApiModule {
    @JvmStatic
    @Provides
    fun providesRetrofit(
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_WEATHER)
            .client(NetworkHelper.createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @JvmStatic
    @Provides
    fun providesWeatherServices(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }
}
