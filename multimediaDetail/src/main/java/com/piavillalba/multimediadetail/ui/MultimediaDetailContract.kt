package com.piavillalba.multimediadetail.ui

import com.piavillalba.core.base.BasePresenter
import com.piavillalba.core.base.BaseView
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail

interface MultimediaDetailContract {

    interface View :
        BaseView {

        fun showSkeleton()
        fun hideSkeleton()
        fun loadMultimediaDetail(multimediaDetail: MultimediaDetail)
    }

    interface Presenter :
        BasePresenter<View> {

        fun onViewCreated(type: MultimediaType, id: String)
    }
}
