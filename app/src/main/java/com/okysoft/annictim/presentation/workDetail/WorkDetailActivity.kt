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
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import com.okysoft.annictim.extension.setImage
import com.okysoft.annictim.presentation.episode.EpisodesFragment
import com.okysoft.annictim.presentation.review.ReviewsFragment
import com.okysoft.domain.model.Work
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        binding.imageView.setImage(work.imageUrl)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pagerAdapter = PagerAdapter(this, work)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

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

    private class PagerAdapter(fragmentActivity: FragmentActivity,
                                     private val work: Work) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = if (work.noEpisodes) 2 else 3

        fun getPageTitle(position: Int): CharSequence {
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

        override fun createFragment(position: Int): Fragment {
            if (work.noEpisodes) {
                return when (position) {
                    0 -> WorkDetailFragment.newInstance(work)
                    1 -> ReviewsFragment.newInstance(work.id)
                    else -> Fragment()
                }
            }
            return when (position) {
                0 -> WorkDetailFragment.newInstance(work)
                1 -> EpisodesFragment.newInstance(work.id)
                2 -> ReviewsFragment.newInstance(work.id)
                else -> Fragment()
            }
        }
    }

}
