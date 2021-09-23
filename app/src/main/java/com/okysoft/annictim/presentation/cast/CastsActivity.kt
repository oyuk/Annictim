package com.okysoft.annictim.presentation.cast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityCastsBinding
import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Work

class CastsActivity : AppCompatActivity() {

    companion object {

        private val WORK_KEY = "WORK_KEY"

        fun createIntent(activity: Context, work: Work): Intent {
            return Intent(activity, CastsActivity::class.java).apply {
                putExtra(WORK_KEY, work.id)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casts)

        val binding = DataBindingUtil.setContentView<ActivityCastsBinding>(this, R.layout.activity_casts)
        binding.toolbar.title = getString(R.string.search)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            val workId = intent.getIntExtra(WORK_KEY, -1)
            val castRequestParam = CastRequestParams(workId = workId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CastsFragment.newInstance(castRequestParam))
                .commit()
        }
    }
}


