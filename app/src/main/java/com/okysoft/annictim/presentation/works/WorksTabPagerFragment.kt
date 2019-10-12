package com.okysoft.annictim.presentation.works


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentWorksTabPagerBinding
import com.okysoft.data.WorkTerm

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.search, menu)
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

    private inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() = 3

        override fun getPageTitle(position: Int): CharSequence
            = when (position) {
            0 -> getString(R.string.current_season)
            1 -> getString(R.string.next_season)
            2 -> getString(R.string.previous_season)
            else -> getString(R.string.current_season)
        }

        override fun getItem(position: Int): Fragment {
            val workTerm = when(position) {
                0 -> WorkTerm.Current
                1 -> WorkTerm.Next
                2 -> WorkTerm.Previous
                else -> WorkTerm.Current
            }
            return WorksFragment.newInstance(com.okysoft.data.WorkRequestParams(workTerm = workTerm, fields = com.okysoft.data.WorkRequestParams.Fields.Feed))
        }

    }

    companion object {
        val TAG = WorksTabPagerFragment::class.java.simpleName
        fun newInstance() = WorksTabPagerFragment()
    }
}
