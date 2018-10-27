package com.okysoft.annictim.Presentation.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.okysoft.annictim.Presentation.Fragment.UserFragment
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    companion object {

        private val USER_ID = "USER_ID"
        private val SHARED_ELEMENT_ID = "SHARED_ELEMENT_ID"

        fun createIntent(activity: Context, userId: Int): Intent {
            return Intent(activity, UserActivity::class.java).apply {
                putExtra(USER_ID, userId)
            }
        }

        fun start(activity: Context, sharedElement: Pair<View, String>, userId: Int) {
            val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(activity as Activity, sharedElement)
            val intent = Intent(activity, UserActivity::class.java).apply {
                putExtra(USER_ID, userId)
                putExtra(SHARED_ELEMENT_ID, sharedElement.second)
            }
            activity.startActivity(intent, options.toBundle())
        }
    }

    private val binding: ActivityUserBinding by lazy {
        DataBindingUtil
                .setContentView<ActivityUserBinding>(
                        this,
                        R.layout.activity_user
                )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        val userId = intent.getIntExtra(USER_ID, -1)
        val sharedElementId = intent.getStringExtra(SHARED_ELEMENT_ID)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, UserFragment.newInstance(userId, sharedElementId))
                .commit()
    }

}
