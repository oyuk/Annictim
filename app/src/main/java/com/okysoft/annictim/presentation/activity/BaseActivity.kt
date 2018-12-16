package com.okysoft.annictim.presentation.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.okysoft.annictim.MeStore
import com.okysoft.annictim.extension.clearStackAndStartActivity
import com.okysoft.annictim.presentation.dialog.CustomDialogFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity(), CustomDialogFragment.Listener {

    @Inject lateinit var meStore: MeStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLogout()
    }

    private fun observeLogout() {
        meStore.logout.observe(this, Observer {
            val dialog = supportFragmentManager.findFragmentByTag(CustomDialogFragment::class.java.name)
            if (dialog != null && dialog.isAdded) return@Observer
            CustomDialogFragment.Builder()
                    .title("ログアウトしました")
                    .message("ログイン画面に戻ります。ログインしてください")
                    .positiveButtonTitle("OK")
                    .show(this@BaseActivity)
        })
    }

    override fun positiveAction() {
        clearStackAndStartActivity(LaunchActivity::class.java)
    }

    override fun negativeAction() {
        // do nothing
    }
}