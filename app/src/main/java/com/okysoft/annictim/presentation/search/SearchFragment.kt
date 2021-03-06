package com.okysoft.annictim.presentation.search


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentSearchBinding
import com.okysoft.annictim.presentation.works.WorksActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setTitle(s ?: "")
            }
        })

        val yearSpinnerAdapter = ArrayAdapter(activity as Context,
            R.layout.support_simple_spinner_dropdown_item,
            createYearSpinnerList())
        binding.yearSpinner.adapter = yearSpinnerAdapter

        val seasonSpinnerAdapter = ArrayAdapter.createFromResource(activity as Context,
            R.array.season_list,
            R.layout.support_simple_spinner_dropdown_item)
        binding.seasonSpinner.adapter = seasonSpinnerAdapter

        binding.yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = yearSpinnerAdapter.getItem(position) ?: ""
                viewModel.selectYear(item)
            }
        }

        binding.seasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = seasonSpinnerAdapter.getItem(position) ?: ""
                viewModel.selectSeason(item.toString())
            }
        }

        binding.searchButton.setOnClickListener {
            viewModel.tappedSearch()
        }
        viewModel.transitionTo.observe(this , Observer {
            activity?.startActivity(WorksActivity.createIntent(activity!!, it!!))
        })
        viewModel.enableSeasonSelect.observe(this, Observer {
            binding.seasonSpinner.isEnabled = it!!
            if (!it) {
                binding.seasonSpinner.setSelection(0)
            }
        })
    }

    private fun createYearSpinnerList(): List<String> {
        val list = mutableListOf<String>()
        val dateFormat = SimpleDateFormat("yyyy", Locale.JAPAN)
        val currentYear = dateFormat.format(Date())
        val currentYearInt = currentYear.toInt()
        for (i in 1.downTo(-20)) {
            list.add((currentYearInt + i).toString() + '年')
        }
        list.add(0, "全体")
        return list
    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName
        fun newInstance(): SearchFragment = SearchFragment()
    }

}
