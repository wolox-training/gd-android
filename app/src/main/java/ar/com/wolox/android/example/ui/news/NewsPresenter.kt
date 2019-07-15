package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback
import okhttp3.ResponseBody
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val mRetrofitServices: RetrofitServices
) : BasePresenter<INewsView>() {

    fun getNews() {

        val call = mRetrofitServices!!.getService(NewsService::class.java).getNews()

        call.enqueue(object : NetworkCallback<List<News>>() {

            override fun onResponseSuccessful(response: List<News>?) {

                if (response!!.isEmpty()) {
                    view.showNoNews()
                } else {
                    view.setNews(response)
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
}