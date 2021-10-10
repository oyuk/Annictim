package com.okysoft.annictim.presentation.person


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.annictim.extension.openUrl
import com.okysoft.annictim.util.compose.Center
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonFragment : Fragment() {

    val personId: Int
        get() = arguments?.getInt(PERSON_ID) ?: 0

    @Inject
    lateinit var factory: PersonViewModelImpl.Factory
    private val viewModel: PersonViewModel by viewModels {
        PersonViewModelImpl.provideFactory(factory, personId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetch()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AnnictimTheme {
                    val state: State<PersonViewModel.State> =
                        viewModel.stateFlow.observeAsState(PersonViewModel.State.Loading)
                    when (val stateValue = state.value) {
                        is PersonViewModel.State.Loading -> {
                            Center(modifier = Modifier.fillMaxWidth(0.4f)) {
                                CircularProgressIndicator()
                            }
                        }
                        is PersonViewModel.State.Error -> {
                            ErrorScreen(resume = { viewModel.fetch() })
                        }
                        is PersonViewModel.State.Success -> {
                            if (stateValue.listItem.isEmpty()) {
                                EmptyScreen()
                                return@AnnictimTheme
                            }
                            PersonList(stateValue.listItem) { url ->
                                openUrl(url)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        val TAG: String = PersonFragment::class.java.simpleName
        const val PERSON_ID = "PERSON_ID"

        fun newInstance(person_id: Int): PersonFragment = PersonFragment().apply {
            val args = Bundle().apply {
                putInt(PERSON_ID, person_id)
            }
            arguments = args
        }

    }

}
