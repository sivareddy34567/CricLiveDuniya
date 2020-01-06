package com.example.cricliveduniya.ui.livescore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.BowrowBinding
import com.example.cricliveduniya.databinding.BowscorerowBinding
import com.example.cricliveduniya.network.Batsman
import com.example.cricliveduniya.network.Bowler
import com.example.cricliveduniya.network.BowlerId

class BowlScoreAdapter : RecyclerView.Adapter<BowlScoreAdapter.BowlScoreAdapterViewHolder>(){

    var bowllist : MutableList<BowlerId> = ArrayList()

    companion object DiffCallback : DiffUtil.ItemCallback<Batsman>() {
        override fun areItemsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemCount(): Int {
        return bowllist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class BowlScoreAdapterViewHolder(private var binding: BowscorerowBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: BowlerId) {
            binding.bowler = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BowlScoreAdapterViewHolder {
        return BowlScoreAdapterViewHolder(BowscorerowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BowlScoreAdapterViewHolder, position: Int) {

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
         holder.bind(bowllist.get(position))
    }

    fun setdata(data: List<BowlerId>?) {
        if (data != null) {
            val bowl = BowlerId("Bowler","O","M","R","W", " N","Wd")
            bowllist = data as MutableList<BowlerId>
            bowllist.add(0,bowl)
            notifyDataSetChanged()
        }
    }



}

