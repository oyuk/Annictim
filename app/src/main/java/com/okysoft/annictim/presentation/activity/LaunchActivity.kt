package com.okysoft.annictim.presentation.activity

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.okysoft.annictim.extension.clearTopAndStartActivity
import com.okysoft.annictim.presentation.viewModel.LaunchViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LaunchActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: LaunchViewModel

    companion object {

        fun start(context: Context) {
            context.clearTopAndStartActivity(Intent(context, LaunchActivity::class.java))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.transition.observe(this, Observer {
            when (it) {
                LaunchViewModel.TransitionType.Login -> {
                    LoginActivity.start(this)
                }
                LaunchViewModel.TransitionType.Main -> {
                    MainActivity.start(this)
                }
            }
            finish()
        })
    }

}