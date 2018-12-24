package com.okysoft.annictim.presentation.fragment


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentSettingBinding
import com.okysoft.annictim.presentation.activity.LaunchActivity
import com.okysoft.annictim.presentation.dialog.CustomDialogFragment
import com.okysoft.annictim.presentation.viewModel.SettingViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingFragment : DaggerFragment(), CustomDialogFragment.Listener {

    private lateinit var binding: FragmentSettingBinding
    @Inject lateinit var viewModel: SettingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.logoutText.setOnClickListener {
            CustomDialogFragment.Builder()
                    .title(getString(R.string.logout))
                    .message(getString(R.string.logout_confirm))
                    .positiveButtonTitle(getString(R.string.OK))
                    .negativeButtonTitle(getString(R.string.CANCEL))
                    .show(this@SettingFragment)
        }
        binding.developerBlogText.setOnClickListener {
            val tabsIntent = CustomTabsIntent.Builder().build()
            tabsIntent.launchUrl(this@SettingFragment.activity, Uri.parse("http://reidr.hatenablog.com/"))
        }
        binding.sourceCodeText.setOnClickListener {
            val tabsIntent = CustomTabsIntent.Builder().build()
            tabsIntent.launchUrl(this@SettingFragment.activity, Uri.parse("https://github.com/oyuk/Annictim"))
        }
        binding.license.setOnClickListener {
            startActivity(Intent(activity, OssLicensesMenuActivity::class.java))
        }
        return binding.root
    }

    override fun positiveAction() {
        viewModel.logout()
        LaunchActivity.clearStackAndStart(this@SettingFragment.activity as Activity)
    }

    override fun negativeAction() {
        // do nothing
    }

    companion object {
        val TAG = SettingFragment::class.java.simpleName
        fun newInstance(): SettingFragment = SettingFragment()
    }

}
