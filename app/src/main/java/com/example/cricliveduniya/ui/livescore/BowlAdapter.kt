package com.example.cricliveduniya.ui.livescore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.BowrowBinding
import com.example.cricliveduniya.network.Batsman
import com.example.cricliveduniya.network.Bowler

class BowlAdapter : RecyclerView.Adapter<BowlAdapter.BowlAdapterViewHolder>(){

    var bowllist : MutableList<Bowler> = ArrayList()

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

    class BowlAdapterViewHolder(private var binding: BowrowBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: Bowler) {
            binding.bowler = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BowlAdapterViewHolder {
        return BowlAdapterViewHolder(BowrowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BowlAdapterViewHolder, position: Int) {

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
         holder.bind(bowllist.get(position))
    }

    fun setdata(data: List<Bowler>?) {
        if (data != null) {
            val bowl = Bowler("0","Bowling","O","M","R","W")
            bowllist = data as MutableList<Bowler>
            bowllist.add(0,bowl)
            notifyDataSetChanged()
        }
    }



}

