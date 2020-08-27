package com.okysoft.annictim.presentation.program


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
import com.okysoft.annictim.databinding.FragmentProgramsBinding
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.data.ProgramRequestParams
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProgramsFragment : Fragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentProgramsBinding
    private val adapter = ProgramsAdapter()

    private val programRequestParams: ProgramRequestParams
        get() =  arguments?.getParcelable(REQUEST_PARAM) ?: ProgramRequestParams()

    @Inject lateinit var factory: ProgramsViewModel.Factory

    private val viewModel: ProgramsViewModel by viewModels {
        ProgramsViewModel.provideFactory(factory, programRequestParams)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_programs, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnLoadMoreListener(layoutManager,this@ProgramsFragment)
        }
        binding.recyclerView.itemAnimator?.changeDuration = 0
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.programs.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.items.accept(it)
        })
        adapter.onClick.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                startActivity(WorkDetailActivity.createIntent(activity!!, it.workResponse))
//            }
        })
        viewModel.refresh()
    }

    override fun onLoadMore(currentPage: Int) {
        viewModel.loadMore.onNext(Unit)
    }

    companion object {
        val TAG = ProgramsFragment::class.java.simpleName
        const val REQUEST_PARAM = "REQUEST_PARAM"

        fun newInstance(requestParams: ProgramRequestParams): ProgramsFragment = ProgramsFragment().apply {
            val args = Bundle().apply {
                putParcelable(REQUEST_PARAM, requestParams)
            }
            arguments = args
        }

    }
}
