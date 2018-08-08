package com.okysoft.annictim.Presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startActivity(LoginActivity.createIntent(this))
    }

}
