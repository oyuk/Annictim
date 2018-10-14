package com.okysoft.annictim.Presentation.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R

class UserActivity : AppCompatActivity() {

    companion object {

        private val USER_ID = "USER_ID"

        fun createIntent(activity: Context, userId: Int): Intent {
            return Intent(activity, UserActivity::class.java).apply {
                putExtra(USER_ID, userId)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }


}
