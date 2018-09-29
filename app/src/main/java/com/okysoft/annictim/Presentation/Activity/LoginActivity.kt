package com.okysoft.annictim.Presentation.Activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import com.okysoft.annictim.Presentation.ViewModel.LoginViewModel
import com.okysoft.annictim.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, LoginActivity::class.java)
    }

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.loginComplete.observe(this@LoginActivity, Observer {
            this@LoginActivity.finish()
        })

        viewModel.openLoginView.observe(this@LoginActivity, Observer {
            val tabsIntent = CustomTabsIntent.Builder().build()
            tabsIntent.launchUrl(this@LoginActivity, it)
        })

        viewModel.onCreate()
    }

    override fun onResume() {
        super.onResume()
        val data = intent.dataString
        data?.let {
            val uri = Uri.parse(data)
            viewModel.fetch(uri)
            return
        }
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onBackPressed() {}

}
