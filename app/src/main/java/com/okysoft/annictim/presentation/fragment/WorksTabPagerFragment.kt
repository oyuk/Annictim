package com.okysoft.annictim.presentation.fragment


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.*
import com.okysoft.annictim.R
import com.okysoft.annictim.api.WorkTerm
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.databinding.FragmentWorksTabPagerBinding
import com.okysoft.annictim.presentation.WorksRequestType
import com.okysoft.annictim.presentation.activity.SearchActivity

class WorksTabPagerFragment : Fragment() {

    private lateinit var binding: FragmentWorksTabPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works_tab_pager, container, false)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.search, menu)
        val item = menu?.findItem(R.id.menu_search)
        item?.setOnMenuItemClickListener { i ->
            activity?.let {
                it.startActivity(SearchActivity.createIntent(it))
            }
            true
        }
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() = 3

        override fun getPageTitle(position: Int): CharSequence
                = when (position) {
            0 -> getString(R.string.current_season)
            1 -> getString(R.string.next_season)
            2 -> getString(R.string.previous_season)
            else -> getString(R.string.current_season)
        }

        override fun getItem(position: Int): Fragment?
                = when (position) {
            0 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.Term(WorkTerm.Current),
                            WorksRequestParamModel.Fields.All
                    )
            )
            1 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.Term(WorkTerm.Next),
                            WorksRequestParamModel.Fields.All
                    )
            )
            2 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.Term(WorkTerm.Previous),
                            WorksRequestParamModel.Fields.All
                    )
            )
            else -> null
        }
    }

    companion object {
        val TAG = WorksTabPagerFragment::class.java.simpleName
        fun newInstance() = WorksTabPagerFragment()
    }
}
