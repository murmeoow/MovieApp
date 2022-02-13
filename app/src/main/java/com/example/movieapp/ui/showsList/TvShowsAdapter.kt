package com.example.movieapp.ui.showsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.TvShowItemBinding
import com.example.movieapp.models.TvShowResponseItem

class TvShowsAdapter(val clickListener: (Int) -> Unit): ListAdapter<TvShowResponseItem, TvShowsAdapter.TvShowsHolder>(
    DiffCallback()
) {

     inner class TvShowsHolder(item: View): RecyclerView.ViewHolder(item){

        val binding = TvShowItemBinding.bind(item)

         fun bind(tvShow: TvShowResponseItem) = with(binding){
             //image.setImageResource(tvShow.image)
             tvName.text = tvShow.name
             tvName.setOnClickListener {
                 clickListener(tvShow.id)
             }
         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_show_item, parent, false)
        return TvShowsHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsHolder, position: Int) {
       holder.bind(getItem(position))
    }
}

class DiffCallback : DiffUtil.ItemCallback<TvShowResponseItem>() {
    override fun areItemsTheSame(oldItem: TvShowResponseItem, newItem: TvShowResponseItem): Boolean {
        return oldItem.id== newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShowResponseItem, newItem: TvShowResponseItem): Boolean {
        return oldItem == newItem
    }
}