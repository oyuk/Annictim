package com.okysoft.annictim.presentation.program


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentProgramsBinding
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.data.ProgramRequestParams
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ProgramsFragment : DaggerFragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentProgramsBinding
    private val adapter = ProgramsAdapter()

    val programRequestParams: com.okysoft.data.ProgramRequestParams
        get() =  arguments?.getParcelable(REQUEST_PARAM) ?: com.okysoft.data.ProgramRequestParams()

    @Inject
    lateinit var viewModel: ProgramsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.programs.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
//            it?.let {
//                startActivity(WorkDetailActivity.createIntent(activity!!, it.workResponse))
//            }
        })
        viewModel.refresh()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_programs, container, false)
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
        return binding.root
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
