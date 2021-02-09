package com.piavillalba.multimedia.ui

import com.piavillalba.core.base.BasePresenter
import com.piavillalba.core.base.BaseView
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.ui.adapter.MultimediaAdapterListener

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
