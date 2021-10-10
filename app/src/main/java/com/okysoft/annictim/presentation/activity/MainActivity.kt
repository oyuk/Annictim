package com.okysoft.annictim.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.annictim.extension.clearTopAndStartActivity
import com.okysoft.infra.ApplicationActionCreator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject lateinit var applicationActionCreator: ApplicationActionCreator

    companion object {
        fun createIntent(activity: Context) = Intent(activity, MainActivity::class.java)

        fun start(context: Context) {
            context.clearTopAndStartActivity(createIntent(context))
        }
    }

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            applicationActionCreator.getMe()
        }
        setContent {
            AnnictimTheme {
                MainScreen()
            }
        }
    }

}
