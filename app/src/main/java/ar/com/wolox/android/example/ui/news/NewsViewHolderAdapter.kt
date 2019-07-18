package ar.com.wolox.android.example.ui.news

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.example.model.News
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import androidx.recyclerview.widget.ListAdapter
import ar.com.wolox.android.R

class NewsViewHolderAdapter(
    private val listener: NewsAdapterView
) : ListAdapter<News, NewsViewHolderAdapter.ViewHolder>(NewsAdapterCallBack()) {

    private var newsList = ArrayList<News>()
    private lateinit var view: View
    private lateinit var context: Context
    internal lateinit var sharedPref: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        Fresco.initialize(context)
        view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_news_item, parent, false)

        sharedPref = context.getSharedPreferences(
                context.resources.getString(R.string.preferences_name),
                Context.MODE_PRIVATE)
        return ViewHolder(view)
    }

    fun setNews(newsList: List<News>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        val userId = sharedPref.getInt(context.resources.getString(R.string.login_user_id), 0)

        news.like = news.likes?.contains(userId) == true

        holder.apply {
            vNewsTitle.text = news.title
            vNewsBody.text = news.text
            vTimeAgo.text = news.getTimeFormated()
            vNewsImage.setImageURI(news.picture)
            vFavButton.isChecked = news.like

            vFavButton.setOnClickListener {
                listener.onToggleClick(news)
                news.like = !news.like
            }

            vNewsContainer.setOnClickListener {
                listener.onItemClick(news)
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var vTimeAgo = item.findViewById<TextView>(R.id.vTimeAgo)
        var vNewsTitle = item.findViewById<TextView>(R.id.vNewsTitle)
        var vNewsBody = item.findViewById<TextView>(R.id.vNewsBody)
        var vNewsImage = item.findViewById<SimpleDraweeView>(R.id.vNewsImage)
        var vFavButton = item.findViewById<ToggleButton>(R.id.vFavButton)
        var vNewsContainer = item.findViewById<ConstraintLayout>(R.id.vNewsContainer)
    }
}
