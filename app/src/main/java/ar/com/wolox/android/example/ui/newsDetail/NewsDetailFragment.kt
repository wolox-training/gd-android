package ar.com.wolox.android.example.ui.newsDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.newsDetail.ImageFullScreenActivity.Companion.IMAGE_URL
import ar.com.wolox.wolmo.core.fragment.WolmoDialogFragment
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.fragment_news.*
import java.io.Serializable
import javax.inject.Inject

class NewsDetailFragment @Inject constructor() : WolmoDialogFragment<NewsDetailPresenter>(), INewsDetailView {

    private lateinit var vNewsDetailPicture: SimpleDraweeView
    private lateinit var vNewsDetailSwipe: SwipeRefreshLayout
    private lateinit var vNewsDetailTitle: TextView
    private lateinit var vNewsDetailText: TextView
    private lateinit var vNewsDetailTimeAgo: TextView
    private lateinit var vNewsDetailLike: ToggleButton
    private lateinit var vToolbar: Toolbar
    private lateinit var vNewsDetailBackArrow: ImageView
    private lateinit var news: News

    override fun layout(): Int = ar.com.wolox.android.R.layout.fragment_news_detail

    override fun init() {

        loadIntentData()
        bindViews()
        loadNewsDetails()

        context?.let {
            presenter.setPreferencesConf(it,
                    getString(R.string.preferences_name),
                    getString(R.string.login_user_id)
            )
        }

        vNewsDetailPicture.setOnClickListener() {
            val imageFullScreenIntent = Intent(activity, ImageFullScreenActivity::class.java)
            imageFullScreenIntent.putExtra(IMAGE_URL, news.picture)
            startActivity(imageFullScreenIntent)
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
        activity?.let { ctx ->
            val news = ctx.intent?.extras?.get("news") as News?
        }
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
        news = arguments!!.getSerializable("news") as News
        vNewsDetailTitle.text = news.title
        vNewsDetailText.text = news.text
        vNewsDetailTimeAgo.text = news.getTimeFormated()
        vNewsDetailPicture.setImageURI(news.picture)
        vNewsDetailLike.isChecked = news.like
    }

    private fun bindViews() {
        // vNewsDetailSwipe = activity!!.findViewById(R.id.vNewsDetailSwipe)
        vNewsDetailTitle = activity!!.findViewById(R.id.vNewsDetailTitle)
        vNewsDetailText = activity!!.findViewById(R.id.vNewsDetailText)
        vNewsDetailTimeAgo = activity!!.findViewById(R.id.vNewsDetailTimeAgo)
        vNewsDetailPicture = activity!!.findViewById(R.id.vNewsDetailPicture)
        vNewsDetailLike = activity!!.findViewById(R.id.vNewsDetailLike)
        vToolbar = activity!!.findViewById(R.id.vToolbar)
        vNewsDetailBackArrow = activity!!.findViewById(R.id.vNewsDetailBackArrow)
        vNewsDetailSwipe = activity!!.findViewById(R.id.vNewsDetailSwipe)
    }

    override fun onToggleSuccess(like: Boolean) {
        vNewsDetailLike?.isSelected = like
    }

    override fun showError() {
        Toast.makeText(context, resources.getString(R.string.login_error_service), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun instance(
                news: Serializable
        ): NewsDetailFragment {
            val newDetailsFragment = NewsDetailFragment()
            var bundle = Bundle()
            bundle.putSerializable("news", news)
            newDetailsFragment.arguments = bundle
            return newDetailsFragment
        }
    }
}
