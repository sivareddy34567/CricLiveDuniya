package com.example.cricliveduniya.ui.home

import androidx.lifecycle.*
import com.example.cricliveduniya.network.AllMatches
import com.example.cricliveduniya.network.Network
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    enum class MarsApiStatus { LOADING, ERROR, DONE }
    // The internal MutableLiveData String that stores the status of the most recent request

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<AllMatches>()

    val properties: LiveData<AllMatches>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    // The external immutable LiveData for the request status String


//    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
//
//    val navigateToSelectedProperty: LiveData<MarsProperty>
//        get() = _navigateToSelectedProperty
//
//    fun displayPropertyDetails(marsProperty: MarsProperty) {
//        _navigateToSelectedProperty.value = marsProperty
//    }
//
//    fun displayPropertyDetailsComplete() {
//        _navigateToSelectedProperty.value = null
//    }

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getAllMatches()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getAllMatches() {
        coroutineScope.launch {
            val getPropertiesDeferred = Network.devbytes.getPlaylist()
            try {
                _status.value = MarsApiStatus.LOADING

                val listResult =  getPropertiesDeferred.await()
                _status.value = MarsApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}