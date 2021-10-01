package com.okysoft.annictim.presentation.workDetail


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorkDetailBinding
import com.okysoft.annictim.extension.openUrl
import com.okysoft.annictim.presentation.cast.CastsAdapter
import com.okysoft.annictim.presentation.person.PersonActivity
import com.okysoft.annictim.presentation.staff.StaffAdapter
import com.okysoft.data.WatchKind
import com.okysoft.domain.model.WorkDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkDetailFragment : Fragment() {

    val work: WorkDetail
        get() =  arguments?.getParcelable(WORK) ?: WorkDetail.default()

    @Inject lateinit var factory: WorkViewModel.Factory
    private val viewModel: WorkViewModel by viewModels {
        WorkViewModel.provideFactory(factory, work.annictId)
    }

    private lateinit var binding: FragmentWorkDetailBinding
    private val castAdapter = CastsAdapter()
    private val staffAdapter = StaffAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.okysoft.annictim.R.layout.fragment_work_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val work = this.work ?: return
        binding.title.text = work.title
//        binding.media.text = "${work.mediaText} ${work.seasonNameText}"

        if (!work.twitterUsername.isNullOrBlank()) {
            binding.twitterLayout.visibility = View.VISIBLE
            binding.twitterLayout.setOnClickListener {
                openUrl("https://twitter.com/${work.twitterUsername}")
            }
        }
        if (!work.twitterHashtag.isNullOrBlank()) {
            binding.hashtagLayout.visibility = View.VISIBLE
            val url = "https://twitter.com/search?q=${work.twitterHashtag}"
            binding.hashtagLayout.setOnClickListener {
                openUrl(url)
            }
        }

        work.wikipediaUrl?.let { url ->
            binding.wikipediaLayout.visibility = View.VISIBLE
            binding.wikipediaLayout.setOnClickListener {
                openUrl(url)
            }
        }

        work.officialSiteUrl?.let { url ->
            binding.internetLayout.visibility = View.VISIBLE
            binding.internetLayout.setOnClickListener {
                openUrl(url)
            }
        }

//            binding.castTextView.setOnClickListener {
//                startActivity(CastsActivity.createIntent(activity!!, ))
//            }

        viewModel.workKind.observe(viewLifecycleOwner, Observer {
            val watchKind = it ?: WatchKind.no_select
            setupStatusSpinner(watchKind)
        })

        binding.castRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.castRecyclerView.adapter = castAdapter

        castAdapter.items.accept(work.casts)
        castAdapter.onClick.observe(viewLifecycleOwner, {
            it.person?.let { person ->
                startActivity(PersonActivity.createIntent(requireActivity(), person.id))
            }
        })

//        viewModel.casts.observe(viewLifecycleOwner, Observer {
//            castAdapter.items.accept(it)
//        })

        binding.staffRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.staffRecyclerView.adapter = staffAdapter

        viewModel.staffs.observe(viewLifecycleOwner, Observer {
            staffAdapter.items.accept(it)
        })

        castAdapter.onClick.observe(viewLifecycleOwner, Observer {
            //            startActivity(PersonActivity.createIntent(this, it!!.person!!.id))
        })
    }

    private fun setupStatusSpinner(watchKind: WatchKind) {
        val statusSpinnerAdapter = ArrayAdapter.createFromResource(activity as Context,
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

        fun newInstance(work: WorkDetail): WorkDetailFragment = WorkDetailFragment().apply {
            val args = Bundle().apply {
                putParcelable(WORK, work)
            }
            arguments = args
        }

    }
}
