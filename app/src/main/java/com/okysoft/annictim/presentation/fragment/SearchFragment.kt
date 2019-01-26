package com.okysoft.annictim.presentation.fragment


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentSearchBinding
import com.okysoft.annictim.presentation.activity.WorksActivity
import com.okysoft.annictim.presentation.viewModel.SearchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG, "query: ${s}")
                viewModel.setTitle(s ?: "")
            }
        })
        binding.searchButton.setOnClickListener {
            viewModel.tappedSearch()
        }
        viewModel.transitionTo.observe(this , Observer {
            activity?.startActivity(WorksActivity.createIntent(activity!!, it!!))
        })

        return binding.root
    }


    companion object {
        val TAG = SearchFragment::class.java.simpleName
        fun newInstance(): SearchFragment = SearchFragment()
    }

}
