package ar.com.wolox.android.example.ui.news

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.newsDetail.NewsDetailActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.viewholder_news_item.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView, NewsAdapterView {

    private val newsAdapter = NewsViewHolderAdapter(this)

    override fun layout(): Int = R.layout.fragment_news

    override fun init() {

        context?.let {
            presenter.setPreferencesConf(it,
                    resources.getString(R.string.preferences_name),
                    resources.getString(R.string.login_user_id))
        }

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
        vNoNewsText.visibility = View.INVISIBLE
        newsAdapter.setNews(newsList)
        newsAdapter.notifyDataSetChanged()
        stopRefreshing()
    }

    fun startNewsDetailActivity(context: Context, news: News) {
        var starter = Intent(context, NewsDetailActivity::class.java)
        starter.putExtra(NEWS, news)
        context.startActivity(starter)
    }

    fun stopRefreshing() {
        vSwipeRefreshNews.isRefreshing = false
    }

    override fun showAPIError(error: Int) {
        Toast.makeText(activity, getText(error), Toast.LENGTH_SHORT).show()
    }

    override fun showNoNews() {
        vNoNewsText.visibility = View.VISIBLE
    }

    override fun onToggleSuccess(like: Boolean) {
        vFavButton?.isChecked = like
    }

    override fun onToggleClick(news: News) {
        presenter.toggleLike(news)
    }

    override fun onItemClick(news: News) {
        context?.let { startNewsDetailActivity(it, news) }
    }

    companion object {
        private const val NEWS = "news"
    }
}