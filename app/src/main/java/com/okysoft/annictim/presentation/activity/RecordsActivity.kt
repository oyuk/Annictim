package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityRecordsBinding
import com.okysoft.annictim.presentation.fragment.RecordsFragment

class RecordsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        val binding = DataBindingUtil.setContentView<ActivityRecordsBinding>(this, R.layout.activity_records)
        binding.toolbar.title = getString(R.string.record)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            val episodeId = intent.getIntExtra(EPISODE_ID, -1)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RecordsFragment.newInstance(episodeId))
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

    companion object {
        val TAG = RecordsActivity::class.java.simpleName
        const val EPISODE_ID = "EPISODE_ID"

        fun createIntent(activity: Context, episodeId: Int): Intent {
            return Intent(activity, RecordsActivity::class.java).apply {
                putExtra(EPISODE_ID, episodeId)
            }
        }

    }
}
