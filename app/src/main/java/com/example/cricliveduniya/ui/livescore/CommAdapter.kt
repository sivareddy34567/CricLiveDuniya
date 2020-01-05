package com.example.cricliveduniya.ui.livescore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.BatrowBinding
import com.example.cricliveduniya.databinding.CommentaryBinding
import com.example.cricliveduniya.network.Batsman
import com.example.cricliveduniya.network.CommentaryLines

class CommAdapter : RecyclerView.Adapter<CommAdapter.BatAdapterViewHolder>(){

    var commlist : MutableList<CommentaryLines> = ArrayList()

    companion object DiffCallback : DiffUtil.ItemCallback<Batsman>() {
        override fun areItemsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Batsman, newItem: Batsman): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemCount(): Int {
        return commlist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class BatAdapterViewHolder(private var binding: CommentaryBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: CommentaryLines) {
            binding.commentary = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatAdapterViewHolder {
        return BatAdapterViewHolder(CommentaryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BatAdapterViewHolder, position: Int) {

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
         holder.bind(commlist.get(position))
    }

    fun setdata(data: List<CommentaryLines>?) {
        if (data != null) {
            commlist = ArrayList()
            for (commlines : CommentaryLines in data){
                if (commlines.comm.isNotEmpty()){
                    commlist.add(commlines)
                }
            }
            notifyDataSetChanged()
        }
    }



}

