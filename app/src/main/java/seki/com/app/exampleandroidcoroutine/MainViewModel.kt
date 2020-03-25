package seki.com.app.exampleandroidcoroutine

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiClient: ApiClient
) : ViewModel() {

    private val _result = MutableLiveData<Resource<String>>()
    val result: LiveData<Resource<String>> = _result.distinctUntilChanged()

    fun start() = viewModelScope.launch(Dispatchers.IO) {
        _result.postValue(Resource.loading())
        Log.d(MainViewModel::class.java.name, Thread.currentThread().name)
        try {
            val response = apiClient.getUser()
            _result.postValue(Resource.success(response.login))
        } catch (e: Exception) {
            _result.postValue(Resource.error(e))
        }
    }

    fun startAndBlockThread() {
        runBlocking {
            Log.d(MainViewModel::class.java.name, Thread.currentThread().name)
            _result.postValue(Resource.loading())
            try {
                val response = apiClient.getUser()
                _result.postValue(Resource.success(response.login))
            } catch (e: Exception) {
                _result.postValue(Resource.error(e))
            }
        }
    }
}