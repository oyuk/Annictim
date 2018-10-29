package com.okysoft.annictim.Presentation.Fragment


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.Presentation.Activity.UserActivity
import com.okysoft.annictim.Presentation.RecordsActionCreator
import com.okysoft.annictim.Presentation.RecordsAdapter
import com.okysoft.annictim.Presentation.ViewModel.RecordsViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentRecordsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject



class RecordsFragment : DaggerFragment() {

    private lateinit var binding: FragmentRecordsBinding
    @Inject lateinit var store: RecordsViewModel
    @Inject lateinit var actionCreator: RecordsActionCreator

    val episodeId: Int
        get() = arguments?.getInt(EPISODE_ID) ?: -1
    private val adapter = RecordsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.records.observe(this, Observer {
            adapter.items.accept(it)
        })
        adapter.onClick.observe(this, Observer {
            it?.let {

            }
        })
        adapter.onClickUser.observe(this, Observer {

            val pair = Pair(it!!.second, "userImageView")

//            val transaction = activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
//            val f = UserFragment.newInstance(it.first, pair.second!!, false)
//            transaction?.replace(R.id.container, f)?.addSharedElement(it.second, "userImageView")?.commit()

            UserActivity.start(this.activity as AppCompatActivity, pair, it.first)
        })
        actionCreator.fetch(episodeId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_records, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecoration)
        return binding.root
    }

    companion object {
        val TAG = RecordsFragment::class.java.simpleName
        const val EPISODE_ID = "EPISODE_ID"

        fun newInstance(episodeId: Int): RecordsFragment = RecordsFragment().apply {
            val args = Bundle().apply {
                putInt(EPISODE_ID, episodeId)
            }
            arguments = args
        }

    }

}
