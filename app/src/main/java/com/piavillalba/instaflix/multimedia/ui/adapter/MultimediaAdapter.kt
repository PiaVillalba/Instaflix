package com.piavillalba.instaflix.multimedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.piavillalba.instaflix.databinding.ItemMultimediaBinding
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem

class MultimediaAdapter(private val listener: MultimediaAdapterListener) :
    ListAdapter<MultimediaItem, MultimediaViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultimediaViewHolder {
        return MultimediaViewHolder(
            ItemMultimediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: MultimediaViewHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener { itemClicked(position) }
            bind(getItem(position))
        }
    }

    private fun itemClicked(position: Int) {
        listener.onItemSelected(getItem(position))
    }

    companion object {
        val diffUtilCallback = object :
            DiffUtil.ItemCallback<MultimediaItem>() {
            override fun areItemsTheSame(oldItem: MultimediaItem, newItem: MultimediaItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MultimediaItem, newItem: MultimediaItem): Boolean =
                oldItem == newItem
        }
    }
}
