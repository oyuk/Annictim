package com.okysoft.annictim.presentation.works

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.data.WorkRequestParams
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "TITLE"
        const val REQUEST_PARAM_MODEL = "REQUEST_PARAM_MODEL"

        fun createIntent(activity: Context,
                         title: String,
                         requestParams: com.okysoft.data.WorkRequestParams): Intent {
            return Intent(activity, WorksActivity::class.java).apply {
                putExtra(TITLE, title)
                putExtra(REQUEST_PARAM_MODEL, requestParams)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AnnictimTheme {
                Scaffold(
                    topBar = { AppBar() }
                ) {
                    val requestParamModel =
                        intent.getParcelableExtra<WorkRequestParams>(REQUEST_PARAM_MODEL)
                            ?: WorkRequestParams()
                    val factory = WorksViewModel.provideFactory(this, requestParamModel)
                    val viewModel =
                        ViewModelProvider(viewModelStore, factory).get(WorksViewModel::class.java)
                    WorkList(viewModel)
                }
            }
        }
    }

    @Composable
    fun AppBar() {
        TopAppBar(
            title = { Text(text = intent.getStringExtra(TITLE) ?: "") },
            navigationIcon = {
                IconButton(onClick = { finish() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back")
                }
            }
        )
    }
}

