package com.piavillalba.multimediadetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.piavillalba.multimediadetail.R
import com.piavillalba.multimediadetail.databinding.FragmentMultimediaDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultimediaDetailFragment : Fragment() {

    private lateinit var binding: FragmentMultimediaDetailBinding
    private val args: MultimediaDetailFragmentArgs by navArgs()
    private val multimediaDetailViewModel: MultimediaDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMultimediaDetailBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = multimediaDetailViewModel
            lifecycleOwner = this@MultimediaDetailFragment
        }
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
        multimediaDetailViewModel.onViewCreated(type, idMultimedia)
    }

}
