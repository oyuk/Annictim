package com.okysoft.annictim.presentation.episode


import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.presentation.record.RecordsActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentEpisodesBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EpisodesFragment : DaggerFragment() {

    private lateinit var binding: FragmentEpisodesBinding

    val workId: Int
        get() = arguments?.getInt(WORK_ID) ?: -1

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
                startActivity(RecordsActivity.createIntent(activity!!, it.id))
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episodes, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
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
