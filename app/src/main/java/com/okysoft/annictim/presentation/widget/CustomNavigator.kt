package com.okysoft.annictim.presentation.widget

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.okysoft.annictim.MeStore
import com.okysoft.annictim.presentation.program.ProgramRequestParams
import com.okysoft.annictim.presentation.program.ProgramsFragment
import com.okysoft.annictim.presentation.user.UserFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@Navigator.Name("custom_fragment")
class CustomNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId : Int,
    private val userId: Int): FragmentNavigator(context, manager, containerId) {

    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?, navigatorExtras: Navigator.Extras?): NavDestination? {
        val tag = destination.id.toString()
        manager.beginTransaction().apply {
            manager.fragments.forEach { detach(it) }

            val fragment = manager.findFragmentByTag(tag)
            if (fragment == null) {
                val f = createFragment(destination, args)
                add(containerId, f, tag)
            } else {
                attach(fragment)
            }

            setPrimaryNavigationFragment(fragment)
            setReorderingAllowed(true)
        }.commit()
        return destination
    }

    private fun createFragment(destination: Destination, bundle: Bundle?): Fragment {
        val className = destination.className
        return when (className) {
            UserFragment::class.java.name -> {
                val b = Bundle().also {
                    it.putInt("USER_ID", userId)
                }
                instantiateFragment(context, manager, className, b)
            }
            ProgramsFragment::class.java.name -> {
                val b = Bundle().also {
                    it.putParcelable("REQUEST_PARAM", ProgramRequestParams())
                }
                instantiateFragment(context, manager, className, b)
            }
            else -> instantiateFragment(context, manager, className, bundle)
        }
    }

}


class CustomNavHostFragment: NavHostFragment() {

    @Inject lateinit var meStore: MeStore

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        val userId = meStore.me.blockingFirst().id
        return CustomNavigator(requireContext(), childFragmentManager, id, userId)
    }

}