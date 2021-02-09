package com.piavillalba.multimedia.ui.adapter

import com.piavillalba.multimedia.domain.model.MultimediaItem

interface MultimediaAdapterListener {

    fun onItemSelected(multimediaItem: MultimediaItem)
}
