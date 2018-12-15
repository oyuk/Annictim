package com.okysoft.annictim.presentation.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityLoginBinding
import com.okysoft.annictim.presentation.viewModel.LoginViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : BaseActivity() {

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


        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.loginButton.setOnClickListener {
            viewModel.onCreate()
        }
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
