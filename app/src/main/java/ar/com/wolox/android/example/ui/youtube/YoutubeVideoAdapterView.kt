package ar.com.wolox.android.example.ui.youtube

import androidx.annotation.NonNull
import com.google.api.services.youtube.model.SearchResult

interface YoutubeVideoAdapterView {
    fun onItemClick(@NonNull result: SearchResult)
}