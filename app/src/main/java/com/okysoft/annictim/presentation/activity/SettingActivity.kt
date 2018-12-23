package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivitySettingBinding
import com.okysoft.annictim.presentation.fragment.SettingFragment

class SettingActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, SettingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val binding = DataBindingUtil.setContentView<ActivitySettingBinding>(this, R.layout.activity_setting)
        binding.toolbar.title = "設定"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SettingFragment.newInstance())
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
