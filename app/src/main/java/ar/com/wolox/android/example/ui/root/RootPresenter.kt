package ar.com.wolox.android.example.ui.root

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.network.UserService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback
import okhttp3.ResponseBody
import javax.inject.Inject

class RootPresenter @Inject constructor(
    private val mRetrofitServices: RetrofitServices
) : BasePresenter<IRootView>() {

    private var mPrefName: String? = null
    private var mPrefEmail: String? = null
    private var mPrefPass: String? = null
    private var mPrefUserId: String? = null

    internal lateinit var sharedPref: SharedPreferences

    /**
     * connectUser()
     */
    fun connectUser() {
        val call = mRetrofitServices.getService(UserService::class.java).getUserLogin(mPrefEmail)

        view.showLoading()

        call.enqueue(object : NetworkCallback<List<User>>() {

            override fun onResponseSuccessful(response: List<User>?) {

                if (response!!.isEmpty() || response[0].password != mPrefPass) {
                    view.dismissLoading()
                    view.openLogin()
                } else {
                    view.dismissLoading()
                    view.openHome()
                }
            }

            override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                view.dismissLoading()
                view.openLogin()
            }

            override fun onCallFailure(t: Throwable) {
                view.dismissLoading()
                view.openLogin()
            }
        })
    }

    /**
     *
     * @param ctx context
     * @return boolean
     */
    fun isNetworkAvaliable(ctx: Context): Boolean {
        val connectivityManager = ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED
    }

    fun setPreferencesConf(
        context: Context,
        prefName: String,
        prefEmail: String,
        prefPass: String,
        prefUserId: String
    ) {
        sharedPref = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE)

        mPrefEmail = sharedPref.getString(prefEmail, defValue)
        mPrefPass = sharedPref.getString(prefPass, defValue)
        mPrefName = sharedPref.getString(prefName, defValue)
        mPrefUserId = sharedPref.getString(prefUserId, defValue)
    }

    companion object {
        const val defValue = ""
    }
}