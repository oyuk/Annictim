package com.okysoft.annictim.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.okysoft.annictim.MeStore
import com.okysoft.annictim.presentation.launch.LaunchActivity
import com.okysoft.annictim.presentation.widget.dialog.CustomDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(), CustomDialogFragment.Listener {

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
        LaunchActivity.start(this)
    }

    override fun negativeAction() {
        // do nothing
    }
}