package com.okysoft.annictim.Presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navigationController = NavigationController(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        startActivity(LoginActivity.createIntent(this))

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
