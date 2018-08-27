package com.okysoft.annictim.Presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R

class WorkDetailActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, WorkDetailActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)

    }

}
