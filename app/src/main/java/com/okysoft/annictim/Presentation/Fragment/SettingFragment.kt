package com.okysoft.annictim.Presentation.Fragment


import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.logoutText.setOnClickListener {

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



    companion object {
        val TAG = SettingFragment::class.java.simpleName
        fun newInstance(): SettingFragment = SettingFragment()
    }

}
