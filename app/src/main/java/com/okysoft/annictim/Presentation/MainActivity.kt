package com.okysoft.annictim.Presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(LoginActivity.createIntent(this))
    }

}
