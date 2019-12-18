package com.example.cricliveduniya.ui.livescore

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cricliveduniya.R
import com.example.cricliveduniya.databinding.LiveScoreFragmentBinding

class LiveScoreFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = LiveScoreFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val marsProperty = LiveScoreFragmentArgs.fromBundle(arguments!!).match

        val viewModelFactory = LiveScoreViewModelFactory(marsProperty, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(LiveScoreViewModel::class.java)
        return binding.root
    }

}
