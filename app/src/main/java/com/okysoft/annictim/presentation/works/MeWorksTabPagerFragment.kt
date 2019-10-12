package com.okysoft.annictim.presentation.works


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentMeWorksTabPagerBinding
import com.okysoft.data.WatchKind

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

        override fun getItem(position: Int): Fragment {
            val meFilterStatus = WatchKind.fromNum(position)
            return WorksFragment.newInstance(com.okysoft.data.WorkRequestParams(
                type = com.okysoft.data.WorkRequestParams.Type.Me,
                status = meFilterStatus.toString(),
                fields = com.okysoft.data.WorkRequestParams.Fields.Feed,
                season = null))
        }
    }

    companion object {
        val TAG = MeWorksTabPagerFragment::class.java.simpleName
        fun newInstance() = MeWorksTabPagerFragment()
    }
}

