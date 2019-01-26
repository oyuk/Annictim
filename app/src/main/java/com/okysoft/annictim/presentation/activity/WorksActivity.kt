package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.databinding.ActivityWorksBinding
import com.okysoft.annictim.presentation.fragment.WorksFragment

class WorksActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun createIntent(activity: Context, requestParamModel: WorksRequestParamModel): Intent {
            return Intent(activity, WorksActivity::class.java).apply {
                putExtra(REQUEST_PARAM_MODEL, requestParamModel)
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
            val requestParamModel = intent.getParcelableExtra<WorksRequestParamModel>(REQUEST_PARAM_MODEL)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, WorksFragment.newInstance(requestParamModel))
                .commit()
        }
    }

}

