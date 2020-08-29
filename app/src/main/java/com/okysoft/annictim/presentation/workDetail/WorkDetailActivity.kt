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
import com.okysoft.domain.model.WorkDetail
import com.okysoft.domain.usecase.WorkDetailUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class WorkDetailActivity : AppCompatActivity(), CoroutineScope {

    private val workId: Int by lazy { intent.getIntExtra(WORK_ID_KEY, 0) }
    private lateinit var binding: ActivityWorkDetailBinding
    @Inject lateinit var usecase: WorkDetailUseCase

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    companion object {

        private val WORK_ID_KEY = "WORK_ID_KEY"

        fun createIntent(activity: Context, workId: Int): Intent {
            return Intent(activity, WorkDetailActivity::class.java).apply {
                putExtra(WORK_ID_KEY, workId)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_detail)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        job = Job()
        launch {
            usecase.get(workId).collect {
               setupView(it)
            }
        }
    }

    private fun setupView(workDetail: WorkDetail) {
        binding.imageView.setImage(workDetail.imageUrl)
        binding.toolbar.title = ""

        val pagerAdapter = PagerAdapter(this, workDetail)
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
                    binding.toolbar.title = workDetail.title
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
                               private val work: WorkDetail) : FragmentStateAdapter(fragmentActivity) {

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
                    1 -> ReviewsFragment.newInstance(work.annictId)
                    else -> Fragment()
                }
            }
            return when (position) {
                0 -> WorkDetailFragment.newInstance(work)
                1 -> EpisodesFragment.newInstance(work.annictId)
                2 -> ReviewsFragment.newInstance(work.annictId)
                else -> Fragment()
            }
        }
    }

}
