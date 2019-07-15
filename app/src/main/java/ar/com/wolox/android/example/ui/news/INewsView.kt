package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.example.model.News

interface INewsView {

    fun setNews(response: List<News>) {}
}