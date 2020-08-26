package com.okysoft.annictim.presentation.launch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.okysoft.annictim.extension.clearTopAndStartActivity
import com.okysoft.annictim.presentation.activity.MainActivity
import com.okysoft.annictim.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity: AppCompatActivity() {

    private val viewModel: LaunchViewModel by viewModels()

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