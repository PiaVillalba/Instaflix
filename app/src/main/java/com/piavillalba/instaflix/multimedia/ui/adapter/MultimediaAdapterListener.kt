package com.piavillalba.instaflix.multimedia.ui.adapter

import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem

interface MultimediaAdapterListener {

    fun onItemSelected(multimediaItem: MultimediaItem)
}
