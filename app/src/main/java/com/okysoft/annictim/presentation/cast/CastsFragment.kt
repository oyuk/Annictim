package com.okysoft.annictim.presentation.cast


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.okysoft.annictim.R
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectIndexed
import javax.inject.Inject

@AndroidEntryPoint
class CastsFragment : Fragment(), LoadMoreScrollListener.Listener {

    val castRequestParams: CastRequestParams
        get() = arguments?.getParcelable(REQUEST_PARAMS) ?: CastRequestParams()

    @Inject lateinit var factory: CastsViewModel.Factory
    private val viewModel: CastsViewModel by viewModels {
        CastsViewModel.provideFactory(factory, castRequestParams)
    }
    private val adapter = CastsAdapter()

    private var isRefreshing = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing)
                    , onRefresh = { viewModel.refresh() }) {
                    CastList(viewModel.casts)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.apply {
//            addOnLoadMoreListener(layoutManager,this@CastsFragment)
//        }
//        binding.swipeRefresh.setOnRefreshListener {
//            viewModel.refresh()
//        }
//        viewModel.casts.observe(viewLifecycleOwner, Observer {
//            binding.swipeRefresh.isRefreshing = false
//            adapter.items.accept(it)
//        })
        adapter.onClick.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    override fun onLoadMore(currentPage: Int) {
        viewModel.loadMore.onNext(Unit)
    }

    @Composable
    fun CastList(castsLiveData: LiveData<List<Cast>>) {
        val casts by castsLiveData.observeAsState(initial = emptyList())
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = casts,
                itemContent = {
                    CastListItem(cast = it)
                }
            )
        }
    }

    companion object {
        val TAG = CastsFragment::class.java.simpleName
        const val REQUEST_PARAMS = "REQUEST_PARAMS"

        fun newInstance(requestParams: CastRequestParams): CastsFragment = CastsFragment().apply {
            val args = Bundle().apply {
                putParcelable(REQUEST_PARAMS, requestParams)
            }
            arguments = args
        }

    }
}
