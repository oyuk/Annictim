package com.okysoft.annictim.presentation.works


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksTabPagerBinding
import com.okysoft.data.WorkTerm

class WorksTabPagerFragment : Fragment() {

    private lateinit var binding: FragmentWorksTabPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_works_tab_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = PagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
//        val item = menu?.findItem(R.id.menu_search)
//        item?.setOnMenuItemClickListener { i ->
//            activity?.let {
//                it.startActivity(SearchActivity.createIntent(it))
//            }
//            true
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
    }

    private inner class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

        override fun getItemCount() = 3

        fun getPageTitle(position: Int): CharSequence
            = when (position) {
            0 -> getString(R.string.current_season)
            1 -> getString(R.string.next_season)
            2 -> getString(R.string.previous_season)
            else -> getString(R.string.current_season)
        }

        override fun createFragment(position: Int): Fragment {
            val workTerm = when(position) {
                0 -> WorkTerm.Current
                1 -> WorkTerm.Next
                2 -> WorkTerm.Previous
                else -> WorkTerm.Current
            }
            return WorksFragment.newInstance(
                com.okysoft.data.WorkRequestParams(season = workTerm.term(), fields = com.okysoft.data.WorkRequestParams.Fields.Feed),
                position)
        }

    }

    companion object {
        val TAG = WorksTabPagerFragment::class.java.simpleName
        fun newInstance() = WorksTabPagerFragment()
    }
}
