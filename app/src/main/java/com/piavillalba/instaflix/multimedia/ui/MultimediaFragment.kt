package com.piavillalba.instaflix.multimedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.piavillalba.instaflix.R
import com.piavillalba.instaflix.databinding.FragmentMultimediaBinding
import com.piavillalba.instaflix.multimedia.constants.ANGLE
import com.piavillalba.instaflix.multimedia.constants.EFECT_ENABLED
import com.piavillalba.instaflix.multimedia.constants.MULTIMEDIA_COUNT
import com.piavillalba.instaflix.multimedia.constants.NUMBER_OF_COLUMNS
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.ui.adapter.MultimediaAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MultimediaFragment : MultimediaContract.View, Fragment() {

    @Inject
    lateinit var presenter: MultimediaContract.Presenter
    private lateinit var binding: FragmentMultimediaBinding
    private lateinit var moviesSkeletonScreen: SkeletonScreen
    private val args by navArgs<MultimediaFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMultimediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpSwipeRefresh()
        presenter.onViewCreated(args.multimediaType)
    }

    private fun setUpRecycler() {
        binding.rvMultimedia.run {
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
            adapter = MultimediaAdapter(this@MultimediaFragment)
        }
    }

    private fun setUpSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.onViewCreated(args.multimediaType)
        }
    }

    override fun showSkeleton() {
        moviesSkeletonScreen = Skeleton
            .bind(binding.rvMultimedia)
            .load(R.layout.item_multimedia_skeleton)
            .shimmer(EFECT_ENABLED)
            .count(MULTIMEDIA_COUNT)
            .color(R.color.skeleton_shimmer_color)
            .angle(ANGLE)
            .show()
    }

    override fun hideSkeleton() {
        moviesSkeletonScreen.hide()
    }

    override fun hideRefresh() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun loadMultimediaList(multimediaItems: List<MultimediaItem>) {
        binding.rvMultimedia.adapter = MultimediaAdapter(this@MultimediaFragment)
        (binding.rvMultimedia.adapter as MultimediaAdapter).submitList(multimediaItems)
    }

    override fun onItemSelected(multimediaItem: MultimediaItem) {
        // TODO: Go to movie detail
    }
}
