package com.okysoft.annictim.Presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.okysoft.annictim.Presentation.ViewModel.LaunchViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LaunchActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: LaunchViewModel

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