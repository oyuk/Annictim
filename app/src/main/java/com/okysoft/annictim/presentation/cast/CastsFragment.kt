package com.okysoft.annictim.presentation.cast


import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentCastsBinding
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CastsFragment : DaggerFragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentCastsBinding

    val castRequestParams: CastRequestParams
        get() = arguments?.getParcelable(REQUEST_PARAMS) ?: CastRequestParams()

    @Inject
    lateinit var viewModel: CastsViewModel
    private val adapter = CastsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.casts.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            it?.let {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_casts, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnLoadMoreListener(layoutManager,this@CastsFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        return binding.root
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
