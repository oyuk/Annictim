package com.okysoft.annictim.presentation.setting


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.FragmentSettingBinding
import com.okysoft.annictim.extension.clearStackAndStartActivity
import com.okysoft.annictim.presentation.launch.LaunchActivity
import com.okysoft.annictim.presentation.widget.dialog.CustomDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(), CustomDialogFragment.Listener {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        val sections = listOf(
            SettingListSection("設定", listOf(
                SettingListItem("ログアウト") {
                    logout()
                }
            )),
            SettingListSection("フィードバック", listOf(
                SettingListItem("作者サイトへ") {
                    openDeveloperBlog()
                }
            )),
            SettingListSection("ライセンス", listOf(
                SettingListItem("ライセンス") {
                    openLicense()
                }
            )),
        )

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SettingItemList(sections = sections)
            }
        }
    }

    private fun logout() {
        CustomDialogFragment.Builder()
            .title(getString(R.string.logout))
            .message(getString(R.string.logout_confirm))
            .positiveButtonTitle(getString(R.string.OK))
            .negativeButtonTitle(getString(R.string.CANCEL))
            .show(this@SettingFragment)
    }

    private fun openDeveloperBlog() {
        val tabsIntent = CustomTabsIntent.Builder().build()
        tabsIntent.launchUrl(this@SettingFragment.activity as Context, Uri.parse("http://reidr.hatenablog.com/"))
    }

    private fun openLicense() {
        startActivity(Intent(activity, OssLicensesMenuActivity::class.java))
    }

    override fun positiveAction() {
        activity?.let {
            viewModel.logout()
            it.clearStackAndStartActivity(LaunchActivity::class.java)
        }
    }

    override fun negativeAction() {
        // do nothing
    }

    companion object {
        val TAG = SettingFragment::class.java.simpleName
        fun newInstance(): SettingFragment = SettingFragment()
    }

}
