package com.piavillalba.multimedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.piavillalba.core.constants.DETAIL_DEEP_LINK
import com.piavillalba.core.constants.DeepLink
import com.piavillalba.core.extensions.startDeepLinkIntent
import com.piavillalba.multimedia.R
import com.piavillalba.multimedia.constants.ANGLE
import com.piavillalba.multimedia.constants.EFECT_ENABLED
import com.piavillalba.multimedia.constants.MULTIMEDIA_COUNT
import com.piavillalba.multimedia.constants.NUMBER_OF_COLUMNS
import com.piavillalba.multimedia.databinding.FragmentMultimediaBinding
import com.piavillalba.multimedia.domain.model.Genre
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.ui.adapter.MultimediaAdapter
import com.piavillalba.multimedia.ui.adapter.MultimediaAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultimediaFragment : Fragment(), MultimediaAdapterListener {

    private lateinit var binding: FragmentMultimediaBinding
    private lateinit var moviesSkeletonScreen: SkeletonScreen

    private val viewModel: MultimediaViewModel by viewModels()

    private val args by navArgs<MultimediaFragmentArgs>()

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

        setUpObservers()
        setUpRecycler()
        setUpSwipeRefresh()
        viewModel.onViewCreated(args.multimediaType)
    }

    private fun setUpObservers() {
        viewModel.showSkeleton.observe(viewLifecycleOwner, Observer {
            showSkeleton()
        })
        viewModel.showGenresDialog.observe(viewLifecycleOwner, Observer {
            showGenresDialog(it)
        })
        viewModel.loadMultimediaList.observe(viewLifecycleOwner, Observer {
            hideRefresh()
            hideSkeleton()
            loadMultimediaList(it)
        })

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
            viewModel.onViewCreated(args.multimediaType)
        }

        binding.fabMultimedia.setOnClickListener {
            viewModel.actionButtomClicked()
        }
    }

    private fun showSkeleton() {
        moviesSkeletonScreen = Skeleton
            .bind(binding.rvMultimedia)
            .load(R.layout.item_multimedia_skeleton)
            .shimmer(EFECT_ENABLED)
            .count(MULTIMEDIA_COUNT)
            .color(R.color.skeleton_shimmer_color)
            .angle(ANGLE)
            .show()
    }

    private fun hideSkeleton() {
        moviesSkeletonScreen?.hide()
    }

    private fun hideRefresh() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun loadMultimediaList(multimediaItems: List<MultimediaItem>) {
        binding.rvMultimedia.adapter = MultimediaAdapter(this)
        (binding.rvMultimedia.adapter as MultimediaAdapter).submitList(multimediaItems)
    }

    private fun showGenresDialog(genres: List<Genre>) {
        val items = genres.map { it.name }

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.genres_title)
            .setItems(items.toTypedArray()) { dialog, idItem ->
                dialog.dismiss()
                val idGenre = genres[idItem].id
                viewModel.onGenreSelected(idGenre)
            }
            .show()
    }

    override fun onItemSelected(multimediaItem: MultimediaItem) {
        val deepLink = "$DETAIL_DEEP_LINK/${multimediaItem.type}/${multimediaItem.id}"
        goToMultimediaDetail(deepLink)
    }

    private fun goToMultimediaDetail(deepLink: DeepLink) {
        view?.startDeepLinkIntent(deepLink)
    }
}
