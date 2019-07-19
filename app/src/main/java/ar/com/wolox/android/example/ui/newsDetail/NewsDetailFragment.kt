package ar.com.wolox.android.example.ui.newsDetail

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoDialogFragment
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news_detail.*
import javax.inject.Inject

class NewsDetailFragment @Inject constructor() : WolmoDialogFragment<NewsDetailPresenter>(), INewsDetailView {

    private lateinit var news: News

    override fun layout(): Int = R.layout.fragment_news_detail

    override fun init() {

        loadIntentData()
        loadNewsDetails()

        context?.let {
            presenter.setPreferencesConf(it,
                    getString(R.string.preferences_name),
                    getString(R.string.login_user_id)
            )
        }

        vNewsDetailPicture.setOnClickListener() {
            startFullScreenImage(activity!!, news.picture)
        }

        vNewsDetailBackArrow.setOnClickListener { activity?.onBackPressed() }

        vNewsDetailLike.setOnClickListener() {
            presenter.toggleLike(news)
        }

        vNewsDetailSwipe.apply {
            setColorSchemeResources(android.R.color.holo_blue_light)
            setOnRefreshListener {
                isRefreshing = true
                presenter.getPost(news.id)
            }
        }
    }

    private fun loadIntentData() {
        val news = arguments!!.getSerializable(NEWS) as News
    }

    override fun getPost(post: News) {
        stopRefreshing()
    }

    fun stopRefreshing() {
        vNewsDetailSwipe.isRefreshing = false
    }

    override fun showAPIError(error: Int) {
        Toast.makeText(context, getText(error), Toast.LENGTH_SHORT).show()
    }

    override fun showNoNews() {
        vNoNewsText.visibility = View.VISIBLE
    }

    private fun loadNewsDetails() {
        news = arguments!!.getSerializable(NEWS) as News
        vNewsDetailTitle.text = news.title
        vNewsDetailText.text = news.text
        vNewsDetailTimeAgo.text = news.getTimeFormated()
        vNewsDetailPicture.setImageURI(news.picture)
        vNewsDetailLike.isChecked = news.like
    }

    override fun onToggleSuccess(like: Boolean) {
        vNewsDetailLike?.isSelected = like
    }

    override fun showError() {
        Toast.makeText(context, resources.getString(R.string.login_error_service), Toast.LENGTH_SHORT).show()
    }

    fun startFullScreenImage(context: Context, image: String) {
        var starter = Intent(context, ImageFullScreenActivity::class.java)
        starter.putExtra(IMAGE_URL, image)
        context.startActivity(starter)
    }

    companion object {
        const val NEWS = "news"
        const val IMAGE_URL = "image_url"
    }
}
