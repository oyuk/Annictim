package com.okysoft.annictim.presentation.setting

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, SettingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val binding = DataBindingUtil.setContentView<ActivitySettingBinding>(this, R.layout.activity_setting)
        binding.toolbar.title = getString(R.string.setting)
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
