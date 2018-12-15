package com.okysoft.annictim.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.MeStore
import com.okysoft.annictim.R
import com.okysoft.annictim.presentation.fragment.MeWorksTabPagerFragment
import com.okysoft.annictim.presentation.fragment.SettingFragment
import com.okysoft.annictim.presentation.fragment.UserFragment
import com.okysoft.annictim.presentation.fragment.WorksTabPagerFragment
import javax.inject.Inject

class NavigationController @Inject constructor(
        activity: AppCompatActivity,
        private val meStore: MeStore
        ) {
    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val transaction = fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, tag)

        if (fragmentManager.isStateSaved) {
            transaction.commitAllowingStateLoss()
        } else {
            transaction.commit()
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

    fun navigateToMe() {
        val userId = meStore.me.blockingFirst().id
        if (userId == 0) { return }
        replaceFragment(UserFragment.newInstance(userId, "100", true), UserFragment.TAG)
    }

}