package com.okysoft.annictim.Presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.design.internal.BottomNavigationItemView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        startActivity(LoginActivity.createIntent(this))

        switchFragment(WorksTabPagerFragment.newInstance(), WorksTabPagerFragment.TAG)


        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
            navigationItem.navigate(navigationController)
            true
        }
    }

    private fun switchFragment(fragment: Fragment, tag: String) {
        if (fragment.isAdded) {
            return
        }
        val currentFragment = supportFragmentManager.findFragmentById(R.id.content)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        currentFragment?.let { fragmentTransaction.detach(it) }
        if (fragment.isDetached) {
            fragmentTransaction.attach(fragment)
        } else {
            fragmentTransaction.add(R.id.container, fragment, tag)
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE).commit()
    }

}
