package com.okysoft.annictim.Presentation.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.Presentation.Fragment.RecordsFragment
import com.okysoft.annictim.R

class RecordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
        val episodeId = intent.getIntExtra(EPISODE_ID, -1)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RecordsFragment.newInstance(episodeId))
                .commit()
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
