package com.okysoft.annictim.presentation.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Rect
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.okysoft.annictim.R
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.databinding.ActivityWorkDetailBinding
import com.okysoft.annictim.extension.openUrl
import com.okysoft.annictim.extension.setImage
import com.okysoft.annictim.presentation.CastsAdapter
import com.okysoft.annictim.presentation.fragment.EpisodesFragment
import com.okysoft.annictim.presentation.fragment.ReviewsFragment
import com.okysoft.annictim.presentation.viewModel.WorkViewModel
import javax.inject.Inject

class WorkDetailActivity : BaseActivity() {

    val work: Work by lazy { intent.getParcelableExtra<Work>(WORK_KEY) }
    private lateinit var binding: ActivityWorkDetailBinding
    private val castAdapter = CastsAdapter()

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
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imageView.setImage(work.images.recommendedUrl)
        binding.title.text = work.title
        binding.media.text = "${work.mediaText} ${work.seasonNameText}"

        binding.castRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.castRecyclerView.adapter = castAdapter

        viewModel.casts.observe(this, Observer {
            castAdapter.items.accept(it)
        })

        castAdapter.onClick.observe(this, Observer {
            startActivity(PersonActivity.createIntent(this, it!!.person!!.id))
        })

        viewModel.staffs.observe(this, Observer {
            binding.staff.text = it?.first()?.name
        })

        work.twitterUsername?.let {userName ->
            binding.twitter.setOnClickListener {
                openUrl("https://twitter.com/${userName}")
            }
        } ?: { binding.twitter.visibility = View.GONE }()

        work.twitterHashtag?.let { hashTag ->
            binding.hashtag.setOnClickListener {
                openUrl("https://twitter.com/search?q=${hashTag}")
            }
        } ?: { binding.hashtag.visibility = View.GONE }()

        work.wikipediaUrl?.let {url ->
            binding.wikipedia.setOnClickListener {
                openUrl(url)
            }
        } ?: { binding.wikipedia.visibility = View.GONE }()

        work.officialSiteUrl?.let {url ->
            binding.internet.setOnClickListener {
                openUrl(url)
            }
        } ?: { binding.internet.visibility = View.GONE }()

        binding.castTextView.setOnClickListener {
            startActivity(CastsActivity.createIntent(this, work))
        }

        val pagerAdapter = PagerAdapter(supportFragmentManager, work.id)
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
