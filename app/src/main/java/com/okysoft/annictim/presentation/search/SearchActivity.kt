package com.okysoft.annictim.presentation.search

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, SearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val binding = DataBindingUtil.setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
        binding.toolbar.title = getString(R.string.search)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commit()
        }
    }

}
