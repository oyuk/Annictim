package com.okysoft.annictim.presentation.record


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentRecordsBinding
import com.okysoft.annictim.presentation.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordsFragment : Fragment() {

    private lateinit var binding: FragmentRecordsBinding
    private val store: RecordsViewModel by viewModels()
    @Inject lateinit var actionCreator: RecordsActionCreator

    val episodeId: Int
        get() = arguments?.getInt(EPISODE_ID) ?: -1
    private val adapter = RecordsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_records, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecoration)
        store.records.observe(viewLifecycleOwner, Observer {
            adapter.items.accept(it)
        })
        adapter.onClick.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
        adapter.onClickUser.observe(viewLifecycleOwner, Observer {

            val pair = Pair(it!!.second, "userImageView")

//            val transaction = activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
//            val f = UserFragment.newInstance(it.first, pair.second!!, false)
//            transaction?.replace(R.id.container, f)?.addSharedElement(it.second, "userImageView")?.commit()

            UserActivity.start(this.activity as AppCompatActivity, pair, it.first)
        })
        actionCreator.fetch(episodeId)
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
