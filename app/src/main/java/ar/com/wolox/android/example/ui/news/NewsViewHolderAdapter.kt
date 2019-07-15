package ar.com.wolox.android.example.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import androidx.recyclerview.widget.ListAdapter

class NewsViewHolderAdapter : ListAdapter<News, NewsViewHolderAdapter.ViewHolder>(NewsAdapterCallBack()) {

    private var newsList = ArrayList<News>()
    private lateinit var view: View
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        Fresco.initialize(context)
        view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_news_item, parent, false)
        return ViewHolder(view)
    }

    fun setNews(newsList: List<News>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
    }

    fun clear() {
        this.newsList.clear()
    }

    fun addNews(newsList: List<News>) {
        this.newsList.addAll(newsList)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]

        holder.apply {
            vNewsTitle.text = news.title
            vNewsBody.text = news.text
            vTimeAgo.text = news.createdAt
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var vTimeAgo = item.findViewById<TextView>(R.id.vTimeAgo)
        var vNewsTitle = item.findViewById<TextView>(R.id.vNewsTitle)
        var vNewsBody = item.findViewById<TextView>(R.id.vNewsBody)
        var vNewsImage = item.findViewById<SimpleDraweeView>(R.id.vNewsImage)
        var vFavButton = item.findViewById<ToggleButton>(R.id.vFavButton)
    }
}
