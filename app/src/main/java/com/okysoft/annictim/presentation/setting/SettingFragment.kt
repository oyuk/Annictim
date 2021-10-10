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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.okysoft.annictim.AnnictimTheme
import com.okysoft.annictim.extension.clearStackAndStartActivity
import com.okysoft.annictim.presentation.launch.LaunchActivity
import com.okysoft.annictim.presentation.widget.dialog.CustomDialog
import com.okysoft.annictim.presentation.widget.dialog.DialogAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AnnictimTheme {
                    SettingItemList(sections = viewModel.sections)
                    CustomDialog(
                        title = "ログアウト",
                        message = "ログアウトしますか？",
                        positiveAction = DialogAction.Positive("OK") {
                            logout()
                        },
                        negativeAction = null,
                        openDialog = viewModel.openDialog
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.openDeveloperBlog.observe(viewLifecycleOwner) {
            openDeveloperBlog()
        }
        viewModel.openLicense.observe(viewLifecycleOwner) {
            openLicense()
        }
    }

    private fun openDeveloperBlog() {
        val tabsIntent = CustomTabsIntent.Builder().build()
        tabsIntent.launchUrl(this@SettingFragment.activity as Context, Uri.parse("http://reidr.hatenablog.com/"))
    }

    private fun openLicense() {
        startActivity(Intent(activity, OssLicensesMenuActivity::class.java))
    }

    private fun logout() {
        activity?.let {
            viewModel.logout()
            it.clearStackAndStartActivity(LaunchActivity::class.java)
        }
    }

    companion object {
        val TAG = SettingFragment::class.java.simpleName
        fun newInstance(): SettingFragment = SettingFragment()
    }

}
