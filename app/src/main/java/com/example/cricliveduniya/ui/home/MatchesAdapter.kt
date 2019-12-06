package com.example.cricliveduniya.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricliveduniya.databinding.MatchItemBinding
import com.example.cricliveduniya.network.Matches

class MatchesAdapter(val onClickListener: OnClickListener) :

    ListAdapter<Matches, MatchesAdapter.MatchesAdapterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Matches>() {
        override fun areItemsTheSame(oldItem: Matches, newItem: Matches): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Matches, newItem: Matches): Boolean {
            return oldItem.match_id == newItem.match_id
        }
    }

    class MatchesAdapterViewHolder(private var binding: MatchItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: Matches) {
            binding.match = marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesAdapter.MatchesAdapterViewHolder {
        return MatchesAdapterViewHolder(MatchItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MatchesAdapter.MatchesAdapterViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class OnClickListener(val clickListener: (marsProperty: Matches) -> Unit) {
        fun onClick(marsProperty:Matches) = clickListener(marsProperty)
    }

}
