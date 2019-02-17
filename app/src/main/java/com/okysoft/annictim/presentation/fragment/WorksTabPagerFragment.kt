package com.okysoft.annictim.presentation.fragment


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.*
import com.okysoft.annictim.R
import com.okysoft.annictim.api.WorkTerm
import com.okysoft.annictim.api.model.WorkRequestParams
import com.okysoft.annictim.databinding.FragmentWorksTabPagerBinding
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

        override fun getItem(position: Int): Fragment? {
            val workTerm = when(position) {
                0 -> WorkTerm.Current
                1 -> WorkTerm.Next
                2 -> WorkTerm.Previous
                else -> WorkTerm.Current
            }
            return WorksFragment.newInstance(WorkRequestParams(workTerm = workTerm))
        }

    }

    companion object {
        val TAG = WorksTabPagerFragment::class.java.simpleName
        fun newInstance() = WorksTabPagerFragment()
    }
}
