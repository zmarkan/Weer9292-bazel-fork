package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApi {

    // Quick singleton implementation
    companion object {
        @Volatile
        private var INSTANCE: WeatherApi? = null

        fun getInstance(): WeatherApi {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = WeatherApi()
                INSTANCE = instance
                return instance
            }
        }
    }

    val service: WeatherServices
        get() {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_WEATHER)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherServices::class.java)
        }
}
