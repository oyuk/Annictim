package com.okysoft.annictim.presentation.user


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentUserBinding
import dagger.android.support.DaggerFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment(private val userId: Int,
                   private val isMe: Boolean,
                   private var sharedElementId: String?) : Fragment() {

    class UserFragmentFactory(private val userId: Int,
                              private val isMe: Boolean,
                              private var sharedElementId: String?): FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            if (className == UserFragment::class.java.name) {
                return UserFragment(userId, isMe, sharedElementId)
            }
            return super.instantiate(classLoader, className)
        }
    }

    private lateinit var binding: FragmentUserBinding
    @Inject lateinit var factory: UserViewModel.Factory
    private val viewModel: UserViewModel by viewModels {
        UserViewModel.provideFactory(
            factory, userId
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
//        binding.toolbar.title = "プロフィール"
//        if (arguments!!.getBoolean(IS_ME)) {
//            binding.toolbar.inflateMenu(R.menu.toolbar_me)
//        }
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.user = it
        })
        viewModel.fetch()
        setHasOptionsMenu(true)
//        binding.toolbar.setOnMenuItemClickListener { item ->
//            when {
//                item.itemId == R.id.menu_settings -> {
//
//                }
//            }
//            true
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        ViewCompat.setTransitionName(binding.userImage, arguments!!.getString(SHARED_ELEMENT_ID))
//        val transitionInflater = TransitionInflater.from(activity)
//
//        sharedElementEnterTransition = transitionInflater.inflateTransition(R.transition.shared_element)
//                        .apply {
//                            addListener(object : Transition.TransitionListener {
//                                override fun onTransitionResume(transition: Transition) {
//                                    Log.d(TAG, "resume")
//                                }
//                                override fun onTransitionPause(transition: Transition) {
//                                    Log.d(TAG, "puase")
//                                }
//                                override fun onTransitionCancel(transition: Transition) {
//                                    Log.d(TAG, "cancel")
//                                }
//                                override fun onTransitionStart(transition: Transition) {
//                                    Log.d(TAG, "start")
//                                }
//                                override fun onTransitionEnd(transition: Transition) {
//                                    Log.d(TAG, "end")
//                                }
//                            })
//                        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_me, menu)
//        val item = menu?.findItem(R.id.menu_settings)
//        item?.setOnMenuItemClickListener { i ->
//            activity?.let {
//                it.startActivity(SettingActivity.createIntent(it))
//            }
//            true
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
    }

    companion object {
        val TAG = UserFragment::class.java.simpleName
    }

}
