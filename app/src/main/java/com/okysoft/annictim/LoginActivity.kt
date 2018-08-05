package com.okysoft.annictim

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val data = intent.dataString
        data?.let {
            val url = Uri.parse(data)
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

}
