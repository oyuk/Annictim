package com.okysoft.annictim.presentation.works

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorksBinding
import com.okysoft.data.WorkRequestParams
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun createIntent(activity: Context, requestParams: com.okysoft.data.WorkRequestParams): Intent {
            return Intent(activity, WorksActivity::class.java).apply {
                putExtra(REQUEST_PARAM_MODEL, requestParams)
            }
        }
    }

    private lateinit var binding: ActivityWorksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_works)
        binding.toolbar.title = getString(R.string.search)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            val requestParamModel = intent.getParcelableExtra<WorkRequestParams>(REQUEST_PARAM_MODEL) ?: WorkRequestParams()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, WorksFragment.newInstance(requestParamModel, null))
                .commit()
        }
    }

}

