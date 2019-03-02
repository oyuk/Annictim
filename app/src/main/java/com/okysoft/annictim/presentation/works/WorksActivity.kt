package com.okysoft.annictim.presentation.works

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.databinding.ActivityWorksBinding

class WorksActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun createIntent(activity: Context, requestParams: WorkRequestParams): Intent {
            return Intent(activity, WorksActivity::class.java).apply {
                putExtra(REQUEST_PARAM_MODEL, requestParams)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val binding = DataBindingUtil.setContentView<ActivityWorksBinding>(this, R.layout.activity_works)
        binding.toolbar.title = getString(R.string.search)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            val requestParamModel = intent.getParcelableExtra<WorkRequestParams>(REQUEST_PARAM_MODEL)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, WorksFragment.newInstance(requestParamModel))
                .commit()
        }
    }

}

