package com.okysoft.annictim.Presentation


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.API.WorkTerm
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksTabPagerBinding

class WorksTabPagerFragment : Fragment() {

    private lateinit var binding: FragmentWorksTabPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works_tab_pager, container, false)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() = 3

        override fun getPageTitle(position: Int): CharSequence
                = when (position) {
            0 -> "Current"
            1 -> "Next"
            2 -> "hoge"
            else -> "Previous"
        }

        override fun getItem(position: Int): Fragment?
                = when (position) {
            0 -> WorksFragment.newInstance(WorksRequestType.Term(WorkTerm.Current))
            1 -> WorksFragment.newInstance(WorksRequestType.Term(WorkTerm.Next))
            2 -> WorksFragment.newInstance(WorksRequestType.Term(WorkTerm.Previous))
            3 -> WorksFragment.newInstance(WorksRequestType.Term(WorkTerm.Current))
            else -> null
        }
    }

    companion object {
        val TAG = WorksTabPagerFragment::class.java.simpleName
        fun newInstance() = WorksTabPagerFragment()
    }
}
