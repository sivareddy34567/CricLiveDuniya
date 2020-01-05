package com.example.cricliveduniya.ui.scorecard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricliveduniya.network.Matches

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class ScoreViewModelFactory(

    val matches: Int,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreCardViewModel::class.java)) {
            return ScoreCardViewModel(matches, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
