package com.okysoft.annictim.presentation.person


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.okysoft.annictim.R
import com.okysoft.annictim.presentation.cast.CastsFragment
import com.okysoft.annictim.presentation.cast.CastsViewModel
import com.okysoft.data.CastRequestParams
import com.okysoft.infra.fragment.Person
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class PersonFragment : Fragment() {

    val personId: Int
        get() = arguments?.getInt(PERSON_ID) ?: 0

    @Inject
    lateinit var factory: PersonViewModel.Factory
    private val viewModel: PersonViewModel by viewModels {
        PersonViewModel.provideFactory(factory, personId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PersonInfo()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateFlow.observe(viewLifecycleOwner, {
            it?.let {

            }
        })
    }

    @Preview
    @Composable
    fun PersonInfo(person: Person) {
        Text(text = "名前")
        Text(text = person.name)
        Text(text = "名前（かな）")
        Text(text = person.nameKana)
    }

    @Preview
    @Composable
    fun PersonInfoItem(title: String, content: String, linkable: Boolean) {

    }

    private fun createPersonItems(): Array<>  {

    }

    companion object {
        val TAG = PersonFragment::class.java.simpleName
        const val PERSON_ID = "PERSON_ID"

        fun newInstance(person_id: Int): PersonFragment = PersonFragment().apply {
            val args = Bundle().apply {
                putInt(PERSON_ID, person_id)
            }
            arguments = args
        }

    }

}
