package nl.tcilegnar.weer9292.network

import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.interceptors.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ApiHelper {
    @JvmStatic
    fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addHeaders()
            .addMockingInterceptor()
            // TODO (PK): .addInterceptor(networkConnectionInterceptor)
            .addLoggingInterceptor()
            .build()
    }

    private fun OkHttpClient.Builder.addHeaders(): OkHttpClient.Builder {
        return addInterceptor { chain ->
            val request = chain.request().newBuilder().apply {
                addHeader("x-rapidapi-host", BuildConfig.RAPID_API_HOST)
                addHeader("x-rapidapi-key", BuildConfig.RAPID_API_KEY)
            }.build()

            chain.proceed(request)
        }
    }

    /**
     * Use mock responses instead of real API call, to prevent hitting the limit of 100 calls a day on a free account:
     * https://rapidapi.com/community/api/open-weather-map/pricing
     */
    private fun OkHttpClient.Builder.addMockingInterceptor(): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            addInterceptor(MockInterceptor())
        }
        return this
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

