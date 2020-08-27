package com.okysoft.annictim.presentation.episode


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentEpisodesBinding
import com.okysoft.annictim.presentation.record.RecordsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding

    val workId: Int
        get() = arguments?.getInt(WORK_ID) ?: -1

    @Inject lateinit var viewModelFactory: EpisodesViewModel.Factory

    private val viewModel: EpisodesViewModel by viewModels {
        EpisodesViewModel.provideFactory(viewModelFactory, workId)
    }

    private val adapter = EpisodesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episodes, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        adapter.onClick.observe(viewLifecycleOwner, Observer {
            it?.let {
                startActivity(RecordsActivity.createIntent(requireActivity(), it.id))
            }
        })
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
