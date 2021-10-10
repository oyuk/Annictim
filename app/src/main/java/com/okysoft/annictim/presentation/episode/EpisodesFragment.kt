package com.okysoft.annictim.presentation.episode


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.annictim.presentation.record.RecordsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    val workId: Int
        get() = arguments?.getInt(WORK_ID) ?: -1

    @Inject lateinit var viewModelFactory: EpisodesViewModel.Factory

    private val viewModel: EpisodesViewModel by viewModels {
        EpisodesViewModel.provideFactory(viewModelFactory, workId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AnnictimTheme {
                    EpisodesView(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tapped.observe(viewLifecycleOwner) {
            startActivity(RecordsActivity.createIntent(requireActivity(), it.id))
        }
        viewModel.fetch()
    }

    companion object {
        val TAG = EpisodesFragment::class.java.simpleName
        const val WORK_ID = "WORK_ID"

        fun newInstance(workId: Int): EpisodesFragment = EpisodesFragment().apply {
            val args = Bundle().apply {
                putInt(WORK_ID, workId)
            }
            arguments = args
        }

    }

}
