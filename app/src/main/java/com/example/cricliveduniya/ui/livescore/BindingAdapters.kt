package com.example.cricliveduniya.ui.livescore
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.R
import com.example.cricliveduniya.network.Batsman
import com.example.cricliveduniya.network.Bowler
import com.example.cricliveduniya.network.CommentaryLines

@BindingAdapter("batData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Batsman>?) {
    val adapter = recyclerView.adapter as BatAdapter
    adapter.setdata(data)
}

@BindingAdapter("bowlData")
fun bindRecyclerView1(recyclerView: RecyclerView, data: List<Bowler>?){
    val adapter = recyclerView.adapter as BowlAdapter
    adapter.setdata(data)
}

@BindingAdapter("commData")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<CommentaryLines>?){
    val adapter = recyclerView.adapter as CommAdapter
    adapter.setdata(data)
}


@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: LiveScoreViewModel.MarsApiStatus?) {
    when (status) {
        LiveScoreViewModel.MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        LiveScoreViewModel.MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        LiveScoreViewModel.MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}



