package com.piavillalba.multimedia.ui

import com.piavillalba.core.base.BasePresenter
import com.piavillalba.core.base.BaseView
import com.piavillalba.core.constants.DeepLink
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.domain.model.Genre
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.ui.adapter.MultimediaAdapterListener

interface MultimediaContract {

    interface View :
        BaseView {

        fun showSkeleton()
        fun hideSkeleton()
        fun hideRefresh()
        fun loadMultimediaList(multimediaItems: List<MultimediaItem>)
        fun goToMultimediaDetail(deepLink: DeepLink)
        fun showGenresDialog(genres: List<Genre>)
    }

    interface Presenter :
        BasePresenter<View>,
        MultimediaAdapterListener {

        fun onViewCreated(type: MultimediaType)
        fun actionButtomClicked()
    }
}
