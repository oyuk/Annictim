package com.okysoft.annictim.presentation.cast

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.infra.api.model.response.Work
import com.okysoft.annictim.databinding.ActivityCastsBinding
import com.okysoft.annictim.infra.api.model.request.CastRequestParams

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
