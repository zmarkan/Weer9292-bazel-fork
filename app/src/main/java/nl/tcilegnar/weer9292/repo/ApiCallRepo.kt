package nl.tcilegnar.weer9292.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.tcilegnar.weer9292.network.util.Mocks

private const val TAG = "API"

abstract class ApiCallRepo<NetworkResponseType, ResponseType>(
    private val mocks: Mocks
) {
    private val _response = MutableLiveData<ResponseType?>(null)
    private val _isLoading = MutableLiveData(false)
    private val _errorMessage = MutableLiveData("")
    val response: LiveData<ResponseType?> = _response
    val isLoading: LiveData<Boolean> = _isLoading
    val errorMessage: LiveData<String> = _errorMessage

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    private fun stopLoading() {
        _isLoading.postValue(false)
    }

    private fun onError(errorMessage: String, throwable: Throwable? = null) {
        stopLoading()
        Log.e(TAG, errorMessage, throwable)
        _errorMessage.postValue(errorMessage)
    }

    protected fun startApiCall(
        startNetworkCall: suspend () -> NetworkResponseType,
        processNetworkResponse: (responseObject: NetworkResponseType) -> ResponseType,
        handleError: (e: Exception) -> String,
        mockData: suspend (mocks: Mocks) -> NetworkResponseType
    ) {
        startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val networkResponse = if (mocks.shouldUseMockedData) mockData(mocks) else startNetworkCall()
                val processedResponse = processNetworkResponse(networkResponse)
                stopLoading()
                _response.postValue(processedResponse)
            } catch (e: Exception) {
                onError(handleError(e), e)
            }
        }
    }
}
