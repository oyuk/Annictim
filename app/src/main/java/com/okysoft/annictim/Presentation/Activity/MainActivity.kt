package com.okysoft.annictim.Presentation.Activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import com.okysoft.annictim.Presentation.NavigationController
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val navigationController = NavigationController(this)
    private lateinit var binding: ActivityMainBinding

    companion object {
        fun createIntent(activity: Context) = Intent(activity, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigationController.navigateToWorks()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
            navigationItem.navigate(navigationController)
            true
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
            navigateToSetting()
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
