package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.okysoft.annictim.R
import com.okysoft.infra.ApplicationActionCreator
import com.okysoft.annictim.databinding.ActivityMainBinding
import com.okysoft.annictim.extension.clearTopAndStartActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

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
        if (savedInstanceState == null) {
            applicationActionCreator.getMe()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(setOf(R.id.works_tab, R.id.me_works, R.id.programs, R.id.user)))
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}
