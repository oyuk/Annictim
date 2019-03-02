package com.okysoft.annictim.presentation.person

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ActivityPersonBinding

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
        binding.toolbar.title = getString(R.string.search)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

//        if (savedInstanceState == null) {
//            val personId = intent.getIntExtra(PERSON_ID, -1)
//            val castRequestParam = CastRequestParams(workId = workId)
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container, CastsFragment.newInstance(castRequestParam))
//                .commit()
//        }
    }

}
