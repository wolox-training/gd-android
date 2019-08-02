package ar.com.wolox.android.example.ui.root

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.R
import ar.com.wolox.android.example.utils.requireNavController
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class RootFragment @Inject constructor() : WolmoFragment<RootPresenter>(), IRootView {
    lateinit var sharedPref: SharedPreferences
    lateinit var dialog: ProgressDialog

    override fun layout(): Int = R.layout.activity_base

    override fun init() {

        sharedPref = activity!!.getSharedPreferences(
                getString(R.string.preferences_name), Context.MODE_PRIVATE)

        presenter.setPreferencesConf(context!!,
                getString(R.string.preferences_name),
                getString(R.string.login_email),
                getString(R.string.login_pass),
                getString(R.string.login_user_id))

        presenter.connectUser()
    }

    override fun openHome() {
        requireNavController().navigate(R.id.action_rootFragment_to_homeFragment)
    }

    override fun openLogin() {
        requireNavController().navigate(R.id.action_rootFragment_to_loginFragment)
    }

    override fun showLoading() {
        dialog = ProgressDialog(activity)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setTitle(R.string.login_dialog_title)
        dialog.setMessage(getString(R.string.login_dialog_message))
        dialog.show()
    }

    override fun dismissLoading() {
        dialog.dismiss()
    }
}