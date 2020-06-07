package nl.tcilegnar.weer9292.dagger.modules.helpers

import nl.tcilegnar.weer9292.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkHelper {
    @JvmStatic
    fun createClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addHeaders()
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

    private fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            addInterceptor(loggingInterceptor)
        }
        return this
    }
}

