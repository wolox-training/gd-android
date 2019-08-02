package ar.com.wolox.android.example.ui.newsList

import ar.com.wolox.android.example.model.News

interface NewsAdapterView {

    fun onToggleClick(news: News)

    fun onItemClick(news: News)
}