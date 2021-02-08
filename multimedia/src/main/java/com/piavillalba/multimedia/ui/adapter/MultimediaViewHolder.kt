package com.piavillalba.multimedia.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.piavillalba.core.extensions.setImage
import com.piavillalba.multimedia.R
import com.piavillalba.multimedia.databinding.ItemMultimediaBinding
import com.piavillalba.multimedia.domain.model.MultimediaItem

class MultimediaViewHolder(private val binding: ItemMultimediaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(multimediaItem: MultimediaItem) {
        with(binding) {
            ivMultimediaPoster.setImage(multimediaItem.image, R.drawable.ic_multimedia_placeholder)
            tvMultimediaTitle.text = multimediaItem.title
            tvMultimediaVoteAverage.text = multimediaItem.voteAverage
        }
    }
}
