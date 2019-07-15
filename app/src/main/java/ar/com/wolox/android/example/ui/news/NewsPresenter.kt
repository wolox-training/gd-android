package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<INewsView>() {

    fun getNews() {
        var newsList: ArrayList<News> = ArrayList()

        for (i in 1..9) {
            newsList.add(News(i, 1, "15min", "Ali Connors", "picture", "I'll be in your neighborhood doing errands"))
        }

        view.setNews(newsList)
    }
}