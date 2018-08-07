package com.okysoft.annictim.Presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.BuildConfig
import com.okysoft.annictim.R

class LoginActivity : AppCompatActivity() {

    companion object {
        fun createIntent(activity: Context) = Intent(activity, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // com.okysoft.annictim.oauth://callback?code=3bf01f787883ae0e2c5f68fb63bb6c2e12af46df061bf4c43df54c783667fb1c

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
