package com.okysoft.annictim.presentation.cast


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
import com.okysoft.annictim.databinding.FragmentCastsBinding
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.data.CastRequestParams
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CastsFragment : Fragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentCastsBinding

    val castRequestParams: CastRequestParams
        get() = arguments?.getParcelable(REQUEST_PARAMS) ?: CastRequestParams()

    @Inject lateinit var factory: CastsViewModel.Factory
    private val viewModel: CastsViewModel by viewModels {
        CastsViewModel.provideFactory(factory, castRequestParams)
    }
    private val adapter = CastsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_casts, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnLoadMoreListener(layoutManager,this@CastsFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.casts.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.items.accept(it)
        })
        adapter.onClick.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    override fun onLoadMore(currentPage: Int) {
        viewModel.loadMore.onNext(Unit)
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
