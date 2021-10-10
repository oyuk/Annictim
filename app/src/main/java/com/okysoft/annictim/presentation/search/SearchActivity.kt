package com.okysoft.annictim.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.annictim.presentation.works.WorksActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels()

    companion object {
        fun createIntent(activity: Context) = Intent(activity, SearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.transitionTo.observe(this , Observer {
            startActivity(WorksActivity.createIntent(this, "検索", it!!))
        })

        setContent {
            AnnictimTheme {
                Scaffold(
                    topBar = { AppBar() }
                ) {
                    SearchScreen {
                        viewModel.search(it)
                    }
                }
            }
        }
    }

    @Composable
    fun AppBar() {
        TopAppBar(
            title = { Text(text = "検索") },
            navigationIcon = {
                IconButton(onClick = { finish() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back")
                }
            }
        )
    }

}
