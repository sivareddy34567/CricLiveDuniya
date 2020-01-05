package com.example.cricliveduniya.ui.scorecard
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.R
import com.example.cricliveduniya.network.*
import com.example.cricliveduniya.ui.livescore.BatScoreAdapter
import com.example.cricliveduniya.ui.livescore.BowlScoreAdapter

@BindingAdapter("batData")
fun bindRecyclerView(recyclerView: RecyclerView, data: InningsItem?) {
    val adapter = recyclerView.adapter as BatScoreAdapter
    adapter.setdata(data?.batsmen)
}

@BindingAdapter("bowlData")
fun bindRecyclerView1(recyclerView: RecyclerView, data: InningsItem?){
    val adapter = recyclerView.adapter as BowlScoreAdapter
    adapter.setdata(data?.bowlers)
}



@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: ScoreCardViewModel.MarsApiStatus?) {
    when (status) {
        ScoreCardViewModel.MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ScoreCardViewModel.MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ScoreCardViewModel.MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}



