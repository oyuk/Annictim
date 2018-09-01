package com.okysoft.annictim.Presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R

class NavigationController constructor(activity: AppCompatActivity) {
    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    private fun replaceFragment(fragment: Fragment, tag: String) {
        if (fragment.isAdded) {
            return
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
        if (fragmentManager.isStateSaved) {
            fragmentTransaction.commitAllowingStateLoss()
        } else {
            fragmentTransaction.commit()
        }
    }

    fun navigateToWorks() {
        replaceFragment(WorksTabPagerFragment.newInstance(), WorksTabPagerFragment.TAG)
    }

    fun navigateToMeWorks() {
        replaceFragment(MeWorksTabPagerFragment.newInstance(), MeWorksTabPagerFragment.TAG)
    }

    fun navigateToSetting() {
        replaceFragment(SettingFragment.newInstance(), SettingFragment.TAG)
    }

}