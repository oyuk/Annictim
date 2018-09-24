package com.okysoft.annictim.Presentation

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.Extension.setImage
import com.okysoft.annictim.Presentation.ViewModel.WorkViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WorkDetailActivity : DaggerAppCompatActivity() {

    val work: Work by lazy { intent.getParcelableExtra<Work>(WORK_KEY) }
    private lateinit var binding: ActivityWorkDetailBinding

    companion object {

        private val WORK_KEY = "WORK_KEY"

        fun createIntent(activity: Context, work: Work): Intent {
            return Intent(activity, WorkDetailActivity::class.java).apply {
                putExtra(WORK_KEY, work)
            }
        }

    }

    @Inject
    lateinit var viewModel: WorkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
        viewModel.work.observe(this, Observer {

        })

        binding.imageView.setImage(work.images.recommendedUrl)
    }

}
