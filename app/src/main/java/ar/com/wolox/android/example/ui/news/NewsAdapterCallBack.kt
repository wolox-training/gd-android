package ar.com.wolox.android.example.ui.news

import androidx.recyclerview.widget.DiffUtil
import ar.com.wolox.android.example.model.News

class NewsAdapterCallBack : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}