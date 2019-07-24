package ar.com.wolox.android.example.ui.youtube

import androidx.recyclerview.widget.DiffUtil
import ar.com.wolox.android.example.model.VideoYoutube

class YoutubeVideoAdapterCallBack : DiffUtil.ItemCallback<VideoYoutube>() {
    override fun areItemsTheSame(oldItem: VideoYoutube, newItem: VideoYoutube): Boolean {
        return oldItem.videoId == newItem.videoId
    }
    override fun areContentsTheSame(oldItem: VideoYoutube, newItem: VideoYoutube): Boolean {
        return oldItem == newItem
    }
}