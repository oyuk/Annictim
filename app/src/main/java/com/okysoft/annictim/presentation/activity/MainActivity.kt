package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import com.okysoft.annictim.ApplicationActionCreator
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding
import com.okysoft.annictim.extension.clearTopAndStartActivity
import com.okysoft.annictim.presentation.NavigationController
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var navigationController: NavigationController
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var applicationActionCreator: ApplicationActionCreator

    companion object {
        fun createIntent(activity: Context) = Intent(activity, MainActivity::class.java)

        fun start(context: Context) {
            context.clearTopAndStartActivity(createIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            applicationActionCreator.getMe()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        navigationController.navigateToWorks()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)

            binding.toolbar.title = navigationItem.titleRes?.let {
                getString(it)
            } ?: ""

            navigationItem.navigate(navigationController)
            true
        }
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            val navigationItem = BottomNavigationItem
                .forId(item.itemId)
            val fragment = supportFragmentManager.findFragmentByTag(navigationItem.name)
            if (fragment is BottomNavigationItem.OnReselectedListener) {
                fragment.onReselected()
            }
        }
    }

    enum class BottomNavigationItem(
        @MenuRes val menuId: Int,
        @StringRes val titleRes: Int?,
        val navigate: NavigationController.() -> Unit
    ) {
        WORKS(R.id.item1, R.string.app_name, {
            navigateToWorks()
        }),

        ME_WORKS(R.id.item2, R.string.me_works, {
            navigateToMeWorks()
        }),

        SETTING(R.id.item3, R.string.user, {
            navigateToMe()
        });

        interface OnReselectedListener {
            fun onReselected()
        }

        companion object {
            fun forId(@IdRes id: Int): BottomNavigationItem {
                return values().first { it.menuId == id }
            }
        }
    }

}
