package com.okysoft.annictim.presentation.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityUserBinding
import com.okysoft.annictim.presentation.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity() {

    companion object {

        private val USER_ID = "USER_ID"
        private val SHARED_ELEMENT_ID = "SHARED_ELEMENT_ID"

        fun createIntent(activity: Context, userId: Int): Intent {
            return Intent(activity, UserActivity::class.java).apply {
                putExtra(USER_ID, userId)
            }
        }

        fun start(activity: AppCompatActivity, sharedElement: Pair<View, String>, userId: Int) {
            val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(activity, sharedElement)
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

        val userId = intent.getIntExtra(USER_ID, -1)
        val sharedElementId = intent.getStringExtra(SHARED_ELEMENT_ID)
        val isMe = meStore.me.blockingFirst()?.id == userId
        val fragmentFactory = UserFragment.UserFragmentFactory(userId, isMe, sharedElementId)
        supportFragmentManager.fragmentFactory = UserFragment.UserFragmentFactory(userId, isMe, sharedElementId)

        setContentView(R.layout.activity_user)

        binding.toolbar.title = getString(R.string.user)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragmentFactory.instantiate(classLoader, UserFragment::class.java.name))
                    .commit()
        }
    }

}
