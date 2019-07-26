package ar.com.wolox.android.example.ui.youtube

import androidx.annotation.NonNull
import com.google.api.services.youtube.model.SearchResult

interface IYoutubeView {

    fun setVideoList(@NonNull list: MutableList<SearchResult>)

    fun showAPIError()

    fun appendVideos(@NonNull videos: MutableList<SearchResult>)
}