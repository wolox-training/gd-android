package ar.com.wolox.android.example.ui.youtube

import androidx.recyclerview.widget.DiffUtil
import com.google.api.services.youtube.model.SearchResult

class YoutubeVideoAdapterCallBack : DiffUtil.ItemCallback<SearchResult>() {
    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem.id.videoId == newItem.id.videoId
    }

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem
    }
}