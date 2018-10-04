package com.okysoft.annictim.Presentation.Fragment


import android.app.Activity
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.API.Model.WorksRequestParamModel
import com.okysoft.annictim.API.WorkTerm
import com.okysoft.annictim.Extension.LoadMoreScrollListener
import com.okysoft.annictim.Extension.addOnLoadMoreListener
import com.okysoft.annictim.Presentation.Activity.WorkDetailActivity
import com.okysoft.annictim.Presentation.ViewModel.WorksViewModel
import com.okysoft.annictim.Presentation.WorksAdapter
import com.okysoft.annictim.Presentation.WorksRequestType
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class WorksFragment : DaggerFragment(), LoadMoreScrollListener.Listener {

    private lateinit var binding: FragmentWorksBinding
    private val adapter = WorksAdapter()

    val worksRequestParamModel: WorksRequestParamModel
        get() =  arguments?.getParcelable(REQUEST_PARAM_MODEL) ?:
        WorksRequestParamModel(WorksRequestType.Term(WorkTerm.Current), WorksRequestParamModel.Fields.All)

    @Inject
    lateinit var viewModel: WorksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.works.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            it?.let {
                val pair = android.support.v4.util.Pair.create(it.imateView as View, "workImageView")
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(activity as Activity, pair)
                startActivity(WorkDetailActivity.createIntent(activity!!, it.work), options.toBundle())
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            addOnLoadMoreListener(layoutManager,this@WorksFragment)
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
        val TAG = WorksFragment::class.java.simpleName
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun newInstance(worksRequestParamModel: WorksRequestParamModel): WorksFragment = WorksFragment().apply {
            val args = Bundle().apply {
                putParcelable(REQUEST_PARAM_MODEL, worksRequestParamModel)
            }
            arguments = args
        }

    }
}
