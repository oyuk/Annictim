package com.okysoft.annictim.presentation.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityLoginBinding
import com.okysoft.annictim.extension.clearStackAndStartActivity
import com.okysoft.annictim.extension.clearTopAndStartActivity
import com.okysoft.annictim.presentation.activity.BaseActivity
import com.okysoft.annictim.presentation.activity.MainActivity
import com.okysoft.annictim.presentation.widget.dialog.ProgressFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    companion object {

        fun createIntent(activity: Context) = Intent(activity, LoginActivity::class.java)

        fun start(context: Context) {
            context.clearTopAndStartActivity(createIntent(context))
        }

    }

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.loginComplete.observe(this@LoginActivity, Observer {
            clearStackAndStartActivity(MainActivity::class.java)
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
            showProgress()
            val uri = Uri.parse(data)
            viewModel.fetch(uri)
            return
        }
    }

    private fun showProgress() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, ProgressFragment.newInstance())
            .commit()
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

}
