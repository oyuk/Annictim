package com.okysoft.annictim.presentation.workDetail

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.appbar.AppBarLayout
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import com.okysoft.annictim.extension.setImage
import com.okysoft.annictim.infra.api.model.response.Work
import com.okysoft.annictim.presentation.episode.EpisodesFragment
import com.okysoft.annictim.presentation.review.ReviewsFragment

class WorkDetailActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
        binding.imageView.setImage(work.images.recommendedUrl)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pagerAdapter = PagerAdapter(supportFragmentManager, work)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        var inToolbar = true
        binding.toolbar.background = getDrawable(R.drawable.toolbar_transition)

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val toolBarHeight: Float = binding.toolbar.height.toFloat()
            val imagesFrameBottomPosition = Rect().also {
                binding.imageView.getGlobalVisibleRect(it)
            }.bottom - toolBarHeight

            val transition = binding.toolbar.background as TransitionDrawable
            if (imagesFrameBottomPosition > 0) {
                if (!inToolbar) {
                    transition.reverseTransition(200)
                    binding.toolbar.title = ""
                    inToolbar = true
                }
            } else {
                if (inToolbar) {
                    transition.startTransition(200)
                    binding.toolbar.title = work.title
                    inToolbar = false
                }
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class PagerAdapter(fm: FragmentManager,
                                     private val work: Work) : FragmentPagerAdapter(fm) {

        override fun getCount() = if (work.noEpisodes) 2 else 3

        override fun getPageTitle(position: Int): CharSequence {
            val reviewTitle = "Reviews(${work.reviewsCount})"
            if (work.noEpisodes) {
                return when (position) {
                    0 -> "詳細"
                    1 -> reviewTitle
                    else -> "Episodes"
                }
            }
            return when (position) {
                0 -> "詳細"
                1 -> "Episodes(${work.episodesCount})"
                2 -> reviewTitle
                else -> "Episodes"
            }
        }

        override fun getItem(position: Int): Fragment? {
            if (work.noEpisodes) {
                return when (position) {
                    0 -> WorkDetailFragment.newInstance(work)
                    1 -> ReviewsFragment.newInstance(work.id)
                    else -> null
                }
            }
            return when (position) {
                0 -> WorkDetailFragment.newInstance(work)
                1 -> EpisodesFragment.newInstance(work.id)
                2 -> ReviewsFragment.newInstance(work.id)
                else -> null
            }
        }
    }

}
