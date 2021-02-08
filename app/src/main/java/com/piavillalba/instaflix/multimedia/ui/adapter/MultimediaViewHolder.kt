package com.piavillalba.instaflix.multimedia.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.piavillalba.core.extensions.setImage
import com.piavillalba.instaflix.R
import com.piavillalba.instaflix.databinding.ItemMultimediaBinding
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem

class MultimediaViewHolder(private val binding: ItemMultimediaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(multimediaItem: MultimediaItem) {
        with(binding) {
            ivMultimediaPoster.setImage(multimediaItem.image, R.drawable.ic_multimedia_placeholder)
            tvMultimediaTitle.text = multimediaItem.title
            tvMultimediaVoteAverage.text = multimediaItem.voteAverage
        }
    }
}
