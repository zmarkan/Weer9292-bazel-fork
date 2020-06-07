package nl.tcilegnar.weer9292.dagger.modules

import dagger.Module
import dagger.Provides
import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.ApiHelper
import nl.tcilegnar.weer9292.network.WeatherServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ApiModule {
    @JvmStatic
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_WEATHER)
        .client(ApiHelper.createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @JvmStatic
    @Provides
    fun providesWeatherServices(retrofit: Retrofit): WeatherServices =
        retrofit.create(WeatherServices::class.java)
}
