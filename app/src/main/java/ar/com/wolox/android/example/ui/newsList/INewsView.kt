package ar.com.wolox.android.example.ui.newsList

import ar.com.wolox.android.example.model.News

interface INewsView {

    fun setNews(response: List<News>) {}

    fun showAPIError(error: Int)

    fun showNoNews()

    fun onToggleSuccess(like: Boolean)
}