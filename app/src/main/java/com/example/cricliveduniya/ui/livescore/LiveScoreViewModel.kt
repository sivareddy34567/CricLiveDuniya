package com.example.cricliveduniya.ui.livescore

import android.app.Application
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import com.example.cricliveduniya.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LiveScoreViewModel(
    matches: Int,
    app: Application
) : AndroidViewModel(app) {


    enum class MarsApiStatus { LOADING, ERROR, DONE }
    // The internal MutableLiveData String that stores the status of the most recent request

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status



    private val _properties = MutableLiveData<Commentary>()

    val properties: LiveData<Commentary>
        get() = _properties

    private val _matchdet = MutableLiveData<MatchDetails>()

    val matchdet: LiveData<MatchDetails>
        get() = _matchdet

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val timer : CountDownTimer

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        val playersnames : HashMap<String,Players> = HashMap()

        private const val COUNTDOWN_PANIC_SECONDS = 10L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 10000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000000L
    }

    private val _navigateToSelectedProperty = MutableLiveData<Int>()

    val navigateToSelectedProperty: LiveData<Int>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(marsProperty: Int) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


    init {
        getmatchdet(matches)
        getCommantary(matches)
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                if(properties.value?.header?.state.equals("inprogress"))
                getCommantary(matches)
            }

        }
        timer.start()
    }

    private fun getmatchdet(matches: Int) {
        coroutineScope.launch {
            val getPropertiesDeferred = Network.devbytes.getMatchDetails(matches)
            try {
                _status.value = MarsApiStatus.LOADING

                val listResult =  getPropertiesDeferred.await()
                for (s in listResult.players){
                    playersnames.put(s.id,s)
                }
                System.out.println(listResult.toString())
                _status.value = MarsApiStatus.DONE
                _matchdet.value = listResult
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    private fun getCommantary(matches: Int) {
        coroutineScope.launch {
            val getPropertiesDeferred = Network.devbytes.getCommentary(matches)
            try {
                _status.value = MarsApiStatus.LOADING

                val listResult =  getPropertiesDeferred.await()
                System.out.println(listResult.toString())
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
        timer.cancel()
    }

}
