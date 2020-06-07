package nl.tcilegnar.weer9292.network.interceptors

import com.google.gson.Gson
import nl.tcilegnar.weer9292.BuildConfig
import nl.tcilegnar.weer9292.network.model.Mocks
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Borrowed from:
 * https://medium.com/ki-labs-engineering/an-easy-way-to-mock-an-api-response-using-retrofit-okhttp-and-interceptor-7968e1f0d050
 */
class MockInterceptor(
    private val mocks: Mocks = Mocks(),
    private val gson: Gson = Gson()
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = gson.toJson(
                when {
                    uri.isEndpoint("weather") -> mocks.mockedCurrentWeatherResponse
                    uri.isEndpoint("find") -> mocks.mockedCurrentWeatherSearchResponse
                    uri.isEndpoint("forecast/daily") -> mocks.mockedDailyForecastResponse
                    else -> throw IllegalArgumentException("Mock for endpoint not found: $uri")
                }
            )

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and bound to be used only with DEBUG mode"
            )
        }
    }

    private fun String.isEndpoint(suffix: String) = startsWith(BuildConfig.BASE_URL_WEATHER + suffix)
}
