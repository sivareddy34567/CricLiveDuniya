package com.example.cricliveduniya.ui.livescore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.BatrowBinding
import com.example.cricliveduniya.databinding.BatscorerowBinding
import com.example.cricliveduniya.network.Batsman
import com.example.cricliveduniya.network.BatsmanId

class BatScoreAdapter : RecyclerView.Adapter<BatScoreAdapter.BatScoreAdapterViewHolder>(){

    var batslist : MutableList<BatsmanId> = ArrayList()

    companion object DiffCallback : DiffUtil.ItemCallback<Batsman>() {
        override fun areItemsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemCount(): Int {
        return batslist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class BatScoreAdapterViewHolder(private var binding: BatscorerowBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: BatsmanId) {
            binding.batsman = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatScoreAdapterViewHolder {
        return BatScoreAdapterViewHolder(BatscorerowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BatScoreAdapterViewHolder, position: Int) {

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
         holder.bind(batslist.get(position))
    }

    fun setdata(data: List<BatsmanId>?) {
        if (data != null) {
            val bat = BatsmanId("Batsman","","R","B","4s","6s")
            batslist = data as MutableList<BatsmanId>
            batslist.add(0,bat)
            notifyDataSetChanged()
        }
    }



}

