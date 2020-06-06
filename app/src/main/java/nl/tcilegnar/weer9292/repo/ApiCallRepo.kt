package nl.tcilegnar.weer9292.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val TAG = "API"

abstract class ApiCallRepo {
    private val _isLoading = MutableLiveData(false)
    private val _errorMessage = MutableLiveData("")
    val isLoading: LiveData<Boolean> = _isLoading
    val errorMessage: LiveData<String> = _errorMessage

    protected fun startLoading() {
        _isLoading.postValue(true)
    }

    protected fun stopLoading() {
        _isLoading.postValue(false)
    }

    protected fun onError(errorMessage: String, throwable: Throwable? = null) {
        stopLoading()
        Log.e(TAG, errorMessage, throwable)
        _errorMessage.postValue(errorMessage)
    }
}
