package com.example.cricliveduniya.ui.scorecard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cricliveduniya.databinding.ScoreCardFragmentBinding
import com.example.cricliveduniya.ui.livescore.*

class ScoreCard : Fragment() {

    private val viewModel: ScoreCardViewModel by lazy {
        ViewModelProviders.of(this).get(ScoreCardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Set the lifecycleOwner so DataBinding can observe LiveData
        val application = requireNotNull(activity).application
        val binding = ScoreCardFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val marsProperty = LiveScoreFragmentArgs.fromBundle(arguments!!).match

        val viewModelFactory = ScoreViewModelFactory(marsProperty, application)
        binding.score = ViewModelProviders.of(
            this, viewModelFactory
        ).get(ScoreCardViewModel::class.java)
        binding.score = viewModel
        binding.batDet.adapter = BatScoreAdapter()
        binding.bowlDet.adapter = BowlScoreAdapter()

        return binding.root
    }

}
