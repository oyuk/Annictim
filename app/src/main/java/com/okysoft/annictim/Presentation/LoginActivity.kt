package com.okysoft.annictim.Presentation

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import com.okysoft.annictim.BuildConfig
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
            finish()
        })

        val data = intent.dataString
        data?.let {
            val uri = Uri.parse(data)
            viewModel.fetc(uri)
            return
        }

        val clientID = BuildConfig.annictClientId
        val clientKey = BuildConfig.annictClientKey

        val baseURL = "https://api.annict.com"
        val params = "/oauth/authorize?client_id=${clientID}&redirect_uri=com.okysoft.annictim.oauth://callback&response_type=code&scope=read+write"

        val uri = Uri.parse(baseURL + params)

        val tabsIntent = CustomTabsIntent.Builder().build()
        tabsIntent.launchUrl(this, uri)
    }

    private fun openLoginBrowser() {

    }

}
