package com.okysoft.annictim.Presentation.Activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.okysoft.annictim.MeStore
import com.okysoft.annictim.Presentation.Dialog.CustomDialogFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject lateinit var meStore: MeStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLogout()
    }

    private fun observeLogout() {
        meStore.logout.observe(this, Observer {
            val dialog = supportFragmentManager.findFragmentByTag(CustomDialogFragment::class.java.name)
            if (dialog != null && dialog.isVisible) return@Observer
            CustomDialogFragment.Builder(this)
                    .title("title")
                    .message("message")
                    .positiveButtonTitle("ok")
                    .listener(object : CustomDialogFragment.Listener {
                        override fun positiveAction() {
                            LaunchActivity.clearStackAndStart(this@BaseActivity)
                        }

                        override fun negativeAction() {
                            // do nothing
                        }
                    })
                    .show()
        })
    }

}