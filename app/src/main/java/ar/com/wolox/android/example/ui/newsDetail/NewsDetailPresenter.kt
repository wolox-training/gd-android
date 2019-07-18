package ar.com.wolox.android.example.ui.newsDetail

import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.NewsLikes
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback
import okhttp3.ResponseBody
import javax.inject.Inject

class NewsDetailPresenter @Inject constructor(
    private val retrofitServices: RetrofitServices
) : BasePresenter<INewsDetailView>() {

    private lateinit var sharedPrefUserId: String
    private lateinit var sharedPrefName: String
    internal lateinit var sharedPref: SharedPreferences

    fun getPost(id: Int) {

        val call = retrofitServices!!.getService(NewsService::class.java).getPostById(id)

        call.enqueue(object : NetworkCallback<News>() {
            override fun onResponseSuccessful(response: News?) {

                if (response != null) {
                    view.getPost(response)
                }
            }

            override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                view.showAPIError(R.string.news_error_api)
            }

            override fun onCallFailure(t: Throwable) {
                view.showAPIError(R.string.news_error_api)
            }
        })
    }

    fun toggleLike(post: News?) {
        val currentUserId = sharedPref.getInt(sharedPrefUserId, 0)
        var like = false
        if (post?.likes?.contains(currentUserId) == true) {
            (post.likes as ArrayList).remove(currentUserId)
        } else {
            (post?.likes as ArrayList).add(currentUserId)
            like = true
        }

        callToggleLike(post.likes, post.id, like)
    }

    private fun callToggleLike(likes: List<Int>?, newsId: Int, like: Boolean) {

        val call = newsId.let { retrofitServices.getService(NewsService::class.java).toogleLike(NewsLikes(likes), it) }

        call.enqueue(object : NetworkCallback<News>() {
            override fun onResponseSuccessful(response: News?) {
                view.onToggleSuccess(like)
            }

            override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                view.showAPIError(R.string.news_error_api)
            }

            override fun onCallFailure(t: Throwable) {
                view.showAPIError(R.string.news_error_api)
            }
        })
    }

    fun setPreferencesConf(
        context: Context,
        prefName: String,
        prefUserId: String
    ) {
        sharedPrefName = prefName
        sharedPrefUserId = prefUserId

        sharedPref = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    }
}