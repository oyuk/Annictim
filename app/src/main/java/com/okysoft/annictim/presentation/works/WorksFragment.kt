package com.okysoft.annictim.presentation.works


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.databinding.FragmentWorksBinding
import com.okysoft.annictim.extension.LoadMoreScrollListener
import com.okysoft.annictim.extension.addOnLoadMoreListener
import com.okysoft.annictim.presentation.workDetail.WorkDetailActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class WorksFragment : DaggerFragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentWorksBinding
    private val adapter = WorksAdapter()

    val workRequestParams: WorkRequestParams
        get() =  arguments?.getParcelable(REQUEST_PARAM_MODEL) ?: WorkRequestParams(season = null)

    @Inject
    lateinit var viewModel: WorksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.works.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.updateItem(it ?: listOf())
        })
        adapter.onClick.observe(this, Observer {
            it?.let {
                val pair = androidx.core.util.Pair.create(it.imateView as View, "workImageView")
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(activity as Activity, pair)
//                startActivity(WorkDetailActivity.createIntent(activity!!, it.work), options.toBundle())
                startActivity(WorkDetailActivity.createIntent(activity!!, it.work))
            }
        })
        viewModel.refresh()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnLoadMoreListener(layoutManager,this@WorksFragment)
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
        val TAG = WorksFragment::class.java.simpleName
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun newInstance(workRequestParams: WorkRequestParams): WorksFragment = WorksFragment().apply {
            val args = Bundle().apply {
                putParcelable(REQUEST_PARAM_MODEL, workRequestParams)
            }
            arguments = args
        }

    }
}
