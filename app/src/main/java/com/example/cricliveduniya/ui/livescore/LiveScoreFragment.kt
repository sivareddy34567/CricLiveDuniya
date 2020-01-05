package com.example.cricliveduniya.ui.livescore

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.cricliveduniya.databinding.LiveScoreFragmentBinding

class LiveScoreFragment : Fragment() {

    private val viewModel: LiveScoreViewModel by lazy {
        ViewModelProviders.of(this).get(LiveScoreViewModel::class.java)
    }

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
        binding.viewModel = viewModel
        binding.batDet.adapter = BatAdapter()
        binding.bowlDet.adapter = BowlAdapter()
        binding.commentary.adapter = CommAdapter()

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(LiveScoreFragmentDirections.actionLiveScoreFragmentToScoreCard(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }

}
