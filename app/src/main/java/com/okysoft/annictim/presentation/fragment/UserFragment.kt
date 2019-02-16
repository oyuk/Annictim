package com.okysoft.annictim.presentation.fragment


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentUserBinding
import com.okysoft.annictim.presentation.activity.SettingActivity
import com.okysoft.annictim.presentation.viewModel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class UserFragment : DaggerFragment() {

    private lateinit var binding: FragmentUserBinding
    @Inject lateinit var viewModel: UserViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
//        binding.toolbar.title = "プロフィール"
//        if (arguments!!.getBoolean(IS_ME)) {
//            binding.toolbar.inflateMenu(R.menu.toolbar_me)
//        }
        viewModel.userId = arguments!!.getInt(USER_ID)
        viewModel.user.observe(this, Observer {
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.toolbar_me, menu)
        val item = menu?.findItem(R.id.menu_settings)
        item?.setOnMenuItemClickListener { i ->
            activity?.let {
                it.startActivity(SettingActivity.createIntent(it))
            }
            true
        }
    }

    companion object {
        val TAG = UserFragment::class.java.simpleName
        const val USER_ID = "USER_ID"
        const val SHARED_ELEMENT_ID = "SHARED_ELEMENT_ID"
        const val IS_ME = "IS_ME"

        fun newInstance(userId: Int, sharedElementId: String, isMe: Boolean): UserFragment = UserFragment().apply {
            val args = Bundle().apply {
                putInt(USER_ID, userId)
                putString(SHARED_ELEMENT_ID, sharedElementId)
                putBoolean(IS_ME, isMe)
            }
            arguments = args
        }
    }

}
