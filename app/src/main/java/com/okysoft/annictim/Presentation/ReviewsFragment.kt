package com.okysoft.annictim.Presentation


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.Presentation.ViewModel.ReviewsViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentReviewsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ReviewsFragment : DaggerFragment() {

    private lateinit var binding: FragmentReviewsBinding

    val workId: Int
        get() = arguments?.getInt(ReviewsFragment.WORK_ID) ?: -1

    @Inject
    lateinit var viewModel: ReviewsViewModel
    private val adapter = ReviewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.reviews.observe(this, Observer {
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            it?.let {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reviews, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        viewModel.fetch()
        return binding.root
    }

    companion object {
        val TAG = ReviewsFragment::class.java.simpleName
        const val WORK_ID = "WORK_ID"

        fun newInstance(workId: Int): ReviewsFragment = ReviewsFragment().apply {
            val args = Bundle().apply {
                putInt(WORK_ID, workId)
            }
            arguments = args
        }

    }

}
