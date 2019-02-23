package com.okysoft.annictim.presentation.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorkDetailBinding
import com.okysoft.annictim.extension.openUrl
import com.okysoft.annictim.presentation.CastsAdapter
import com.okysoft.annictim.presentation.StaffAdapter
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
    private val staffAdapter = StaffAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.okysoft.annictim.R.layout.fragment_work_detail, container, false)

        val statusSpinnerAdapter = ArrayAdapter.createFromResource(activity,
            R.array.status_list,
            R.layout.support_simple_spinner_dropdown_item)

        binding.statusSpinner.adapter = statusSpinnerAdapter
        binding.statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = statusSpinnerAdapter.getItem(position) ?: ""
                viewModel.updateStatus(workId, item.toString())
            }
        }

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

        viewModel.workKind.observe(this, Observer {
            val position = statusSpinnerAdapter.getPosition(it?.toJA())
            binding.statusSpinner.setSelection(position)
        })

        binding.castRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.castRecyclerView.adapter = castAdapter

        viewModel.casts.observe(this, Observer {
            castAdapter.items.accept(it)
        })

        binding.staffRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.staffRecyclerView.adapter = staffAdapter

        viewModel.staffs.observe(this, Observer {
            staffAdapter.items.accept(it)
        })

        castAdapter.onClick.observe(this, Observer {
            //            startActivity(PersonActivity.createIntent(this, it!!.person!!.id))
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