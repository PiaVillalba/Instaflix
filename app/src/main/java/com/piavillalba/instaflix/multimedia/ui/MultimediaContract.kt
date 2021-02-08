package com.piavillalba.instaflix.multimedia.ui

import com.piavillalba.instaflix.BasePresenter
import com.piavillalba.instaflix.BaseView
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.ui.adapter.MultimediaAdapterListener

interface MultimediaContract {

    interface View :
        BaseView,
        MultimediaAdapterListener {

        fun showSkeleton()
        fun hideSkeleton()
        fun hideRefresh()
        fun loadMultimediaList(multimediaItems: List<MultimediaItem>)
    }

    interface Presenter :
        BasePresenter<View> {

        fun onViewCreated(type: MultimediaType)
    }
}
