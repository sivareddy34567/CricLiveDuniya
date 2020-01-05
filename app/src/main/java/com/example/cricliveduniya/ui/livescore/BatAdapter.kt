package com.example.cricliveduniya.ui.livescore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.BatrowBinding
import com.example.cricliveduniya.network.Batsman

class BatAdapter : RecyclerView.Adapter<BatAdapter.BatAdapterViewHolder>(){

    var batslist : MutableList<Batsman> = ArrayList()

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

    class BatAdapterViewHolder(private var binding: BatrowBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: Batsman) {
            binding.batsman = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatAdapterViewHolder {
        return BatAdapterViewHolder(BatrowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BatAdapterViewHolder, position: Int) {

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
         holder.bind(batslist.get(position))
    }

    fun setdata(data: List<Batsman>?) {
        if (data != null) {
            val bat = Batsman("0","Batting","SR","R","B","4s","6s")
            batslist = data as MutableList<Batsman>
            batslist.add(0,bat)
            notifyDataSetChanged()
        }
    }



}

