package com.narasimha.diceroller.marsrealestate.detail

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.R
import com.example.cricliveduniya.network.Matches
import com.example.cricliveduniya.ui.home.HomeViewModel
import com.example.cricliveduniya.ui.home.MatchesAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Matches>?) {
    val adapter = recyclerView.adapter as MatchesAdapter
    adapter.submitList(data)
}


@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: HomeViewModel.MarsApiStatus?) {
    when (status) {
        HomeViewModel.MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HomeViewModel.MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        HomeViewModel.MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}



