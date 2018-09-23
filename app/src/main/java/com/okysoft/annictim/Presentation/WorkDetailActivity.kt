package com.okysoft.annictim.Presentation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.Presentation.ViewModel.WorkViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import javax.inject.Inject

class WorkDetailActivity : AppCompatActivity() {

    private lateinit var work: Work
    private lateinit var binding: ActivityWorkDetailBinding

    companion object {

        private val WORK_KEY = "WORK_KEY"

        fun createIntent(activity: Context, work: Work): Intent {
            return Intent(activity, WorkDetailActivity::class.java).apply {
                putExtra(WORK_KEY, work)
            }
        }

    }

    val workId: Int
        get() = intent.getParcelableExtra<Work>(WORK_KEY).id

    @Inject
    lateinit var viewModel: WorkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
        work = intent.getParcelableExtra(WORK_KEY)
    }

}
