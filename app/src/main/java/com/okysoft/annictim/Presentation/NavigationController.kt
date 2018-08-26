package com.okysoft.annictim.Presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R

class NavigationController constructor(activity: AppCompatActivity) {
    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    private fun replaceFragment(fragment: Fragment, tag: String) {
        if (fragment.isAdded) {
            return
        }
        val currentFragment = fragmentManager.findFragmentById(containerId)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, tag)
        currentFragment?.let { fragmentTransaction.detach(it) }
        if (fragment.isDetached) {
            fragmentTransaction.attach(fragment)
        } else {
            fragmentTransaction.add(containerId, fragment, tag)
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE).commit()
    }

    fun navigateToWorks() {
        replaceFragment(WorksTabPagerFragment.newInstance(), WorksTabPagerFragment.TAG)
    }

}