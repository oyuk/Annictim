package com.okysoft.annictim.presentation.fragment


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorkDetailBinding
import com.okysoft.annictim.extension.openUrl
import com.okysoft.annictim.presentation.CastsAdapter
import com.okysoft.annictim.presentation.viewModel.WorkViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class WorkDetailFragment : DaggerFragment() {

    val workId: Int
        get() =  arguments?.getInt(WorkDetailFragment.WORK_ID) ?: -1

    @Inject
    lateinit var viewModel: WorkViewModel
    private lateinit var binding: FragmentWorkDetailBinding
    private val castAdapter = CastsAdapter()
//    private val staffAdapter = StaffAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_detail, container, false)

        viewModel.work.observe(this, Observer {
            binding.title.text = it?.title
            binding.media.text = "${it?.mediaText} ${it?.seasonNameText}"

            it?.twitterUsername?.let {userName ->
                binding.twitter.setOnClickListener {
                    openUrl("https://twitter.com/${userName}")
                }
            } ?: { binding.twitter.visibility = View.GONE }()

            it?.twitterHashtag?.let { hashTag ->
                binding.hashtag.setOnClickListener {
                    openUrl("https://twitter.com/search?q=${hashTag}")
                }
            } ?: { binding.hashtag.visibility = View.GONE }()

            it?.wikipediaUrl?.let {url ->
                binding.wikipedia.setOnClickListener {
                    openUrl(url)
                }
            } ?: { binding.wikipedia.visibility = View.GONE }()

            it?.officialSiteUrl?.let {url ->
                binding.internet.setOnClickListener {
                    openUrl(url)
                }
            } ?: { binding.internet.visibility = View.GONE }()

            binding.castTextView.setOnClickListener {
//                startActivity(CastsActivity.createIntent(activity!!, ))
            }
        })

        binding.castRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.castRecyclerView.adapter = castAdapter

        viewModel.casts.observe(this, Observer {
            castAdapter.items.accept(it)
        })

//        binding.staffRecyclerView.layoutManager = GridLayoutManager(activity, 2)
//        binding.staffRecyclerView.adapter = staffAdapter
//
//        viewModel.staffs.observe(this, Observer {
//            staffAdapter.items.accept(it)
//        })

        castAdapter.onClick.observe(this, Observer {
            //            startActivity(PersonActivity.createIntent(this, it!!.person!!.id))
        })

        viewModel.staffs.observe(this, Observer {
            binding.staff.text = it?.first()?.name
        })

        return binding.root
    }

    companion object {
        val TAG = WorkDetailFragment::class.java.simpleName
        const val WORK_ID = "WORK_ID"

        fun newInstance(workId: Int): WorkDetailFragment = WorkDetailFragment().apply {
            val args = Bundle().apply {
                putInt(WORK_ID, workId)
            }
            arguments = args
        }

    }
}
