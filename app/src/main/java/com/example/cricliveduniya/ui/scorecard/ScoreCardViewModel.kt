package com.example.cricliveduniya.ui.scorecard

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cricliveduniya.network.Commentary
import com.example.cricliveduniya.network.Network
import com.example.cricliveduniya.network.ScoreCard
import com.example.cricliveduniya.ui.livescore.LiveScoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScoreCardViewModel(matches: Int,
                         app: Application
) : AndroidViewModel(app) {

    enum class MarsApiStatus { LOADING, ERROR, DONE }
    // The internal MutableLiveData String that stores the status of the most recent request

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<ScoreCard>()

    val properties: LiveData<ScoreCard>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<Int>()

    val navigateToSelectedProperty: LiveData<Int>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val timer : CountDownTimer

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        private const val COUNTDOWN_PANIC_SECONDS = 10L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 10000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000000L
    }

    init {
        getScorecard(matches)
        timer = object : CountDownTimer(
            COUNTDOWN_TIME,
            ONE_SECOND
        ){
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                if(properties.value?.state.equals("inprogress"))
                    getScorecard(matches)
            }

        }
        timer.start()
    }

    private fun getScorecard(matches: Int) {
        coroutineScope.launch {
            val getPropertiesDeferred = Network.devbytes.getScorecard(matches)
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

    fun onShowData(id : Int){
        _navigateToSelectedProperty.value = id
    }




}
