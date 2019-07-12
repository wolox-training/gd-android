package ar.com.wolox.android.example.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News

class NewsViewHolderAdapter : RecyclerView.Adapter<NewsViewHolderAdapter.ViewHolder>() {

    private var newsList = ArrayList<News>()
    private lateinit var view: View
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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

            vFavButton.apply {
                vFavButton.setOnClickListener() {
                    when (vFavButton.tag) {
                        LIKE_PRESSED -> {
                            setImageResource(R.drawable.ic_like_off)
                            tag = LIKE_NON_PRESSED
                        }
                        else -> {
                            setImageResource(R.drawable.ic_like_on)
                            tag = LIKE_PRESSED
                        }
                    }
                }
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var vTimeAgo = item.findViewById<TextView>(R.id.vTimeAgo)
        var vNewsTitle = item.findViewById<TextView>(R.id.vNewsTitle)
        var vNewsBody = item.findViewById<TextView>(R.id.vNewsBody)
        var vNewsImage = item.findViewById<ImageView>(R.id.vNewsImage)
        var vFavButton = item.findViewById<ImageButton>(R.id.vFavButton)
    }

    companion object {
        private const val LIKE_PRESSED = "pressed"
        private const val LIKE_NON_PRESSED = "nonpressed"
    }
}