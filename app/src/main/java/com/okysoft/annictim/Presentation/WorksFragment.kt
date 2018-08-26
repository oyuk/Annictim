package com.okysoft.annictim.Presentation


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.API.WorkTerm
import com.okysoft.annictim.Presentation.ViewModel.WorksViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WorksFragment : DaggerFragment() {

    private lateinit var binding: FragmentWorksBinding
    private val adapter = WorkAdapter()

    val workTerm: String
        get() =  arguments?.getString(WorksFragment.WORK_TERM) ?: ""

    @Inject
    lateinit var viewModel: WorksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.works.observe(this, Observer {
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            startActivity(WorkDetailActivity.createIntent(activity!!))
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.apply {
            adapter = adapter
            addOnScrollListener(LoadMoreScrollListener(layoutManager))
        }
        viewModel.onCreate()
        return binding.root
    }

    companion object {
        val TAG = WorksFragment::class.java.simpleName
        const val WORK_TERM = "WORK_TERM"

        fun newInstance(workTerm: WorkTerm): WorksFragment = WorksFragment().apply {
            val args = Bundle().apply {
                putString(WORK_TERM, workTerm.term())
            }
            arguments = args
        }
    }
}
