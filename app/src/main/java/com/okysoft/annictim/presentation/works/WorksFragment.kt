package com.okysoft.annictim.presentation.works


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.presentation.workDetail.WorkDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorksFragment : Fragment(), LoadMoreScrollListener.Listener {

//    private lateinit var binding: FragmentWorksBinding
//    private val adapter = WorksAdapter()

    private val workRequestParams: com.okysoft.data.WorkRequestParams
        get() =  arguments?.getParcelable(REQUEST_PARAM_MODEL) ?: com.okysoft.data.WorkRequestParams(season = null)

    private val position: Int?
        get() = arguments?.getInt(POSITION)

    @Inject lateinit var factory: WorksViewModel.Factory

    private val viewModel: WorksViewModel by lazy {
        val parent = parentFragment
        val (target, key) = when (parent) {
            is WorksTabPagerFragment -> {
                Pair(parent, "${parent.javaClass.name}${position}")
            }
            is MeWorksTabPagerFragment -> {
                Pair(parent, "${parent.javaClass.name}${position}")
            }
            else -> {
                Pair(this, null)
            }
        }
        val factory = WorksViewModel.provideFactory(factory, workRequestParams)
        return@lazy key?.let {
            ViewModelProvider(target, factory).get(it, WorksViewModel::class.java)
        } ?: ViewModelProvider(target, factory).get(WorksViewModel::class.java)
    }

    private var isRefreshing = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works, container, false)
//        viewModel.refresh()
//        return binding.root
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = { viewModel.refresh() }
                ) {
                    WorkList(list = viewModel.works, onClick = {
                        startActivity(WorkDetailActivity.createIntent(requireActivity(), it))
                    })
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.apply {
//            addOnLoadMoreListener(layoutManager,this@WorksFragment)
//        }
//        binding.recyclerView.itemAnimator?.changeDuration = 0
//        binding.swipeRefresh.setOnRefreshListener {
//            viewModel.refresh()
//        }
//        viewModel.works.observe(viewLifecycleOwner, Observer {
//            binding.swipeRefresh.isRefreshing = false
//            adapter.submitList(it)
//        })
//        adapter.onClick.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                startActivity(WorkDetailActivity.createIntent(requireActivity(), it.annictId))
//            }
//        })
//    }

    override fun onLoadMore(currentPage: Int) {
//        viewModel.loadMore.onNext(Unit)
    }

    companion object {
        val TAG = WorksFragment::class.java.simpleName
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"
        const val POSITION = "POSITION"

        fun newInstance(workRequestParams: com.okysoft.data.WorkRequestParams, position: Int?): WorksFragment = WorksFragment().apply {
            val args = Bundle().apply {
                putParcelable(REQUEST_PARAM_MODEL, workRequestParams)
                position?.let { putInt(POSITION, it) }
            }
            arguments = args
        }

    }
}
