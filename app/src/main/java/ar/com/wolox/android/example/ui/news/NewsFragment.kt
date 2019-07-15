package ar.com.wolox.android.example.ui.news

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    private val newsAdapter = NewsViewHolderAdapter()

    override fun layout(): Int = R.layout.fragment_news

    override fun init() {

        vSwipeRefreshNews.apply {
            vSwipeRefreshNews.setColorSchemeResources(android.R.color.holo_blue_light)
            vSwipeRefreshNews.setOnRefreshListener {
                vSwipeRefreshNews.isRefreshing = true
                presenter.getNews()
            }
        }

        vNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        presenter.getNews()

        vFabBtn.attachToRecyclerView(vNewsRecyclerView)
        vFabBtn.apply {
            setOnClickListener() {
                val intent = Intent(activity, NewsCreationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun setNews(newsList: List<News>) {
        vNoNewsText.apply {
            visibility = View.INVISIBLE
        }
        newsAdapter.setNews(newsList)
        newsAdapter.notifyDataSetChanged()
        stopRefreshing()
    }

    fun stopRefreshing() {
        vSwipeRefreshNews.isRefreshing = false
    }

    override fun showAPIError(error: Int) {
        Toast.makeText(activity, getText(error), Toast.LENGTH_SHORT).show()
    }

    override fun showNoNews() {
        vNoNewsText.apply {
            visibility = View.VISIBLE
        }
    }
}