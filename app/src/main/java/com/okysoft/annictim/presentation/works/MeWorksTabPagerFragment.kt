package com.okysoft.annictim.presentation.works


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentMeWorksTabPagerBinding
import com.okysoft.data.WatchKind

class MeWorksTabPagerFragment : Fragment() {

    private lateinit var binding: FragmentMeWorksTabPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_works_tab_pager, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = PagerAdapter(requireActivity())
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()
    }

    private class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = WatchKind.meKindCount

        fun getPageTitle(position: Int): CharSequence {
            return WatchKind.fromNum(position).toDisplayName()
        }

        override fun createFragment(position: Int): Fragment {
            val meFilterStatus = WatchKind.fromNum(position)
            return WorksFragment.newInstance(com.okysoft.data.WorkRequestParams(
                type = com.okysoft.data.WorkRequestParams.Type.Me,
                status = meFilterStatus.toString(),
                fields = com.okysoft.data.WorkRequestParams.Fields.Feed,
                season = null),
            position)
        }
    }

    companion object {
        val TAG = MeWorksTabPagerFragment::class.java.simpleName
        fun newInstance() = MeWorksTabPagerFragment()
    }
}

