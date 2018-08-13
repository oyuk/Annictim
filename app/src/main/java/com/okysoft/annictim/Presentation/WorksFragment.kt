package com.okysoft.annictim.Presentation


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksBinding

class WorksFragment : Fragment() {

    private lateinit var binding: FragmentWorksBinding
    private val adapter = WorkAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        return binding.root
    }


    companion object {
        val TAG = WorksFragment::class.java.simpleName
        const val ID = "ID"
        fun newInstance(sessionId: String): WorksFragment = WorksFragment().apply {
            val args = Bundle().apply {
                putString(ID, sessionId)
            }
            arguments = args
        }
    }

}
