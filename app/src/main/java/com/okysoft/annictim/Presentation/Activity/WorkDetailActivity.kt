package com.okysoft.annictim.Presentation.Activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.Extension.setImage
import com.okysoft.annictim.Presentation.Fragment.EpisodesFragment
import com.okysoft.annictim.Presentation.Fragment.ReviewsFragment
import com.okysoft.annictim.Presentation.ViewModel.WorkViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import javax.inject.Inject

class WorkDetailActivity : BaseActivity() {

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
        setSupportActionBar(binding.toolbar)
        viewModel.work.observe(this, Observer {

        })

        binding.imageView.setImage(work.images.recommendedUrl)
        binding.title.text = work.title
        binding.media.text = "${work.mediaText} ${work.seasonNameText}"
        binding.episodesCount.text = work.episodesCount.toString()
        binding.watchersCount.text = work.watchersCount.toString()

        val pagerAdapter = PagerAdapter(supportFragmentManager, work.id)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private inner class PagerAdapter(fm: FragmentManager,
                                     private val workId: Int) : FragmentPagerAdapter(fm) {

        override fun getCount() = 2

        override fun getPageTitle(position: Int): CharSequence
                = when (position) {
            0 -> "Episodes"
            1 -> "Reviews"
            else -> "Episodes"
        }

        override fun getItem(position: Int): Fragment?
                = when (position) {
            0 -> EpisodesFragment.newInstance(workId)
            1 -> ReviewsFragment.newInstance(workId)
            else -> null
        }
    }

}
