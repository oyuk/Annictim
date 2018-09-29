package com.okysoft.annictim.Presentation


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.Presentation.ViewModel.EpisodesViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentEpisodesBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EpisodesFragment : DaggerFragment() {

    private lateinit var binding: FragmentEpisodesBinding

    val workId: Int
        get() = arguments?.getInt(EpisodesFragment.WORK_ID) ?: -1

    @Inject
    lateinit var viewModel: EpisodesViewModel
    private val adapter = EpisodesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.episodes.observe(this, Observer {
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            it?.let {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episodes, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnScrollListener(LoadMoreScrollListener(layoutManager))
        }
        viewModel.fetch()
        return binding.root
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
