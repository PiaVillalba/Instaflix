package com.piavillalba.multimediadetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.piavillalba.core.extensions.setImage
import com.piavillalba.multimediadetail.R
import com.piavillalba.multimediadetail.databinding.FragmentMultimediaDetailBinding
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MultimediaDetailFragment : MultimediaDetailContract.View, Fragment() {

    @Inject
    lateinit var presenter: MultimediaDetailContract.Presenter
    private lateinit var binding: FragmentMultimediaDetailBinding
    val args: MultimediaDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMultimediaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgumentsParams()
        setupToolbar()
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun getArgumentsParams() {
        val type = args.multimediaType
        val idMultimedia = args.id
        presenter.onViewCreated(type, idMultimedia)
    }

    override fun showSkeleton() {
        TODO("Not yet implemented")
    }

    override fun hideSkeleton() {
        TODO("Not yet implemented")
    }

    override fun loadMultimediaDetail(multimediaDetail: MultimediaDetail) {
        binding.apply {
            with(multimediaDetail) {
                tvDetailTitle.text = title
                tvDetailsubtitle.text = subtitle
                tvDetailOverview.text = overview
                tvDetailRating.text = voteAverage
                ivDetailPath.setImage(image, R.drawable.ic_image_preview)
                ivDetailPoster.setImage(poster, R.drawable.ic_image_preview)
            }
        }
    }
}
