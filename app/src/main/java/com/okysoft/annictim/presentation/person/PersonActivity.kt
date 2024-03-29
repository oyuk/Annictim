package com.okysoft.annictim.presentation.person

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityPersonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonActivity : AppCompatActivity() {

    companion object {

        private val PERSON_ID = "PERSON_ID"

        fun createIntent(activity: Context, personId:Int): Intent {
            return Intent(activity, PersonActivity::class.java).apply {
                putExtra(PERSON_ID, personId)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casts)

        val binding = DataBindingUtil.setContentView<ActivityPersonBinding>(this, R.layout.activity_person)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val personId = intent.getIntExtra(PERSON_ID, -1)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PersonFragment.newInstance(personId))
                .commit()
        }
    }

}
