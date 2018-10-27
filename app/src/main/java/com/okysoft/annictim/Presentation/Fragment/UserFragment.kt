package com.okysoft.annictim.Presentation.Fragment


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.Presentation.ViewModel.UserViewModel
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentUserBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class UserFragment : DaggerFragment() {

    private lateinit var binding: FragmentUserBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userId = arguments!!.getInt(USER_ID)
        viewModel.user.observe(this, Observer {
            binding.user = it
        })
        ViewCompat.setTransitionName(binding.userImage, arguments!!.getString(SHARED_ELEMENT_ID))
        viewModel.fetch()
    }

    companion object {
        val TAG = UserFragment::class.java.simpleName
        const val USER_ID = "USER_ID"
        const val SHARED_ELEMENT_ID = "SHARED_ELEMENT_ID"

        fun newInstance(userId: Int, sharedElementId: String): UserFragment = UserFragment().apply {
            val args = Bundle().apply {
                putInt(USER_ID, userId)
                putString(SHARED_ELEMENT_ID, sharedElementId)
            }
            arguments = args
        }
    }

}