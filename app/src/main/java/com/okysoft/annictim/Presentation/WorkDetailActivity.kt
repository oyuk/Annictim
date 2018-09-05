package com.okysoft.annictim.Presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.R

class WorkDetailActivity : AppCompatActivity() {

    private lateinit var work: Work

    companion object {

        private val WORK_KEY = "WORK_KEY"

        fun createIntent(activity: Context, work: Work): Intent {
            val intent = Intent(activity, WorkDetailActivity::class.java).apply {
                putExtra(WORK_KEY, work)
            }
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)
        work = intent.getParcelableExtra(WORK_KEY)
    }

}
