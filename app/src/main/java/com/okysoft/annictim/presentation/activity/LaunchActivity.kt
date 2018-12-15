package com.okysoft.annictim.presentation.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import com.okysoft.annictim.presentation.viewModel.LaunchViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LaunchActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: LaunchViewModel

    companion object {

        fun clearStackAndStart(activity: Activity) {
            val intent = Intent(activity, LaunchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.transition.observe(this, Observer {
            when (it) {
                LaunchViewModel.TransitionType.Login -> {
                    startActivity(LoginActivity.createIntent(this))
                }
                LaunchViewModel.TransitionType.Main -> {
                    startActivity(MainActivity.createIntent(this))
                }
            }
            finish()
        })
    }

}