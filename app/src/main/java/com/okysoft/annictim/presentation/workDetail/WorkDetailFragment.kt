package com.okysoft.annictim.presentation.workDetail


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
import com.okysoft.annictim.infra.api.model.response.Work
import com.okysoft.annictim.presentation.WatchKind
import com.okysoft.annictim.presentation.cast.CastsAdapter
import com.okysoft.annictim.presentation.staff.StaffAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WorkDetailFragment : DaggerFragment() {

    val work: Work
        get() =  arguments?.getParcelable(WORK) ?: Work.default()

    @Inject
    lateinit var viewModel: WorkViewModel
    private lateinit var binding: FragmentWorkDetailBinding
    private val castAdapter = CastsAdapter()
    private val staffAdapter = StaffAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.okysoft.annictim.R.layout.fragment_work_detail, container, false)

        viewModel.work.observe(this, Observer { work ->
            binding.title.text = work.title
            binding.media.text = "${work.mediaText} ${work.seasonNameText}"

            if (work.twitterUsername.isNotBlank()) {
                binding.twitterLayout.visibility = View.VISIBLE
                binding.twitterLayout.setOnClickListener {
                    openUrl("https://twitter.com/${work.twitterUsername}")
                }
            }
            if (work.twitterHashtag.isNotBlank()) {
                binding.hashtagLayout.visibility = View.VISIBLE
                val url = "https://twitter.com/search?q=${work.twitterHashtag}"
                binding.hashtagLayout.setOnClickListener {
                    openUrl(url)
                }
            }
            if (work.wikipediaUrl.isNotBlank()) {
                binding.wikipediaLayout.visibility = View.VISIBLE
                binding.wikipediaLayout.setOnClickListener {
                    openUrl(work.wikipediaUrl)
                }
            }
            if (work.officialSiteUrl.isNotBlank()) {
                binding.internetLayout.visibility = View.VISIBLE
                binding.internetLayout.setOnClickListener {
                    openUrl(work.officialSiteUrl)
                }
            }
//            binding.castTextView.setOnClickListener {
//                startActivity(CastsActivity.createIntent(activity!!, ))
//            }
        })

        viewModel.workKind.observe(this, Observer {
            val watchKind = it ?: WatchKind.no_select
            setupStatusSpinner(watchKind)
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

    private fun setupStatusSpinner(watchKind: WatchKind) {
        val statusSpinnerAdapter = ArrayAdapter.createFromResource(activity,
            R.array.status_list,
            R.layout.support_simple_spinner_dropdown_item)
        binding.statusSpinner.adapter = statusSpinnerAdapter
        val position = statusSpinnerAdapter.getPosition(watchKind.toJA())
        binding.statusSpinner.setSelection(position)
        binding.statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = statusSpinnerAdapter.getItem(position) ?: ""
                viewModel.updateStatus(item.toString())
            }
        }
    }

    companion object {
        val TAG = WorkDetailFragment::class.java.simpleName
        const val WORK = "WORK"

        fun newInstance(work: Work): WorkDetailFragment = WorkDetailFragment().apply {
            val args = Bundle().apply {
                putParcelable(WORK, work)
            }
            arguments = args
        }

    }
}
