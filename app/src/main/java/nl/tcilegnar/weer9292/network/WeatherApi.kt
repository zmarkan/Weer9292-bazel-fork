package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.BuildConfig.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                .addHeaders()
                // TODO (PK): .addInterceptor(networkConnectionInterceptor)
                .addLoggingInterceptor()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_WEATHER)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherServices::class.java)
        }

    private fun OkHttpClient.Builder.addHeaders(): OkHttpClient.Builder {
        return addInterceptor { chain ->
            val request = chain.request().newBuilder().apply {
                addHeader("x-rapidapi-host", RAPID_API_HOST)
                addHeader("x-rapidapi-key", RAPID_API_KEY)
            }.build()

            chain.proceed(request)
        }
    }

    private fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            addInterceptor(loggingInterceptor)
        }
        return this
    }
}
