package com.okysoft.annictim.Presentation.Activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import com.okysoft.annictim.ApplicationActionCreator
import com.okysoft.annictim.Presentation.NavigationController
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val navigationController = NavigationController(this)
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var applicationActionCreator: ApplicationActionCreator

    companion object {
        fun createIntent(activity: Context) = Intent(activity, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            applicationActionCreator.getMe()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigationController.navigateToWorks()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
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
            @DrawableRes val imageRes: Int?,
            val isUseToolbarElevation: Boolean,
            val navigate: NavigationController.() -> Unit
    ) {
        WORKS(R.id.item1, null, R.drawable.abc_btn_check_material, false, {
            navigateToWorks()
        }),

        ME_WORKS(R.id.item2, null, R.drawable.abc_btn_check_material, false, {
            navigateToMeWorks()
        }),

        SETTING(R.id.item3, null, R.drawable.abc_btn_check_material, false, {
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
