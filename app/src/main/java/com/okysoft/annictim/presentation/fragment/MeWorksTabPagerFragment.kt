package com.okysoft.annictim.presentation.fragment


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.api.model.WorkRequestParams
import com.okysoft.annictim.databinding.FragmentMeWorksTabPagerBinding
import com.okysoft.annictim.presentation.WatchKind

class MeWorksTabPagerFragment : Fragment() {

    private lateinit var binding: FragmentMeWorksTabPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_works_tab_pager, container, false)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() = WatchKind.meKindCount

        override fun getPageTitle(position: Int): CharSequence {
            return WatchKind.fromNum(position).toDisplayName()
        }

        override fun getItem(position: Int): Fragment? {
            val meFilterStatus = WatchKind.fromNum(position)
            return WorksFragment.newInstance(WorkRequestParams(type = WorkRequestParams.Type.Me,
                status = meFilterStatus.toString(), season = null))
        }

    }

    companion object {
        val TAG = MeWorksTabPagerFragment::class.java.simpleName
        fun newInstance() = MeWorksTabPagerFragment()
    }
}

