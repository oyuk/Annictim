package com.okysoft.annictim.Presentation


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.API.Model.WorksRequestParamModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentMeWorksTabPagerBinding

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

        override fun getCount() = 4

        override fun getPageTitle(position: Int): CharSequence
                = when (position) {
            0 -> MeFilterStatus.watching.toString()
            1 -> MeFilterStatus.wanna_watch.toString()
            2 -> MeFilterStatus.watched.toString()
            3 -> MeFilterStatus.on_hold.toString()
            4 -> MeFilterStatus.stop_watching.toString()
            else -> "Next"
        }

        override fun getItem(position: Int): Fragment?
                = when (position) {
            0 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.MeFilterStatus(MeFilterStatus.wanna_watch),
                            WorksRequestParamModel.Fields.Feed
                    )
            )
            1 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.MeFilterStatus(MeFilterStatus.watched),
                            WorksRequestParamModel.Fields.Feed
                    )
            )
            2 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.MeFilterStatus(MeFilterStatus.on_hold),
                            WorksRequestParamModel.Fields.Feed
                    )
            )
            3 -> WorksFragment.newInstance(
                    WorksRequestParamModel(
                            WorksRequestType.MeFilterStatus(MeFilterStatus.stop_watching),
                            WorksRequestParamModel.Fields.Feed
                    )
            )
            else -> null
        }
    }

    companion object {
        val TAG = MeWorksTabPagerFragment::class.java.simpleName
        fun newInstance() = MeWorksTabPagerFragment()
    }
}

