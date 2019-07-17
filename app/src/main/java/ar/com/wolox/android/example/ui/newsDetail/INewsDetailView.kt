package ar.com.wolox.android.example.ui.newsDetail

import ar.com.wolox.android.example.model.News

interface INewsDetailView {

    fun getPost(response: News) {}

    fun showAPIError(error: Int)

    fun showNoNews()

    fun onToggleSuccess(like: Boolean)

    fun showError()
}