package ar.com.wolox.android.example.ui.youtube

import com.google.api.services.youtube.model.SearchResult

interface YoutubeVideoAdapterView {
    fun onItemClick(result: SearchResult)
}