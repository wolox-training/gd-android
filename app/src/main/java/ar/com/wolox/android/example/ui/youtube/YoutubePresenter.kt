package ar.com.wolox.android.example.ui.youtube

import android.util.Log
import androidx.annotation.NonNull
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import java.net.UnknownHostException
import javax.inject.Inject

class YoutubePresenter @Inject constructor() : BasePresenter<IYoutubeView>() {

    private val mYouTube = YouTube
            .Builder(AndroidHttp.newCompatibleTransport(), JacksonFactory.getDefaultInstance(), null)
            .build()

    private var nextPageToken: String? = null
    private var isLoading = false

    fun onYouTubeSearch(@NonNull query: String, @NonNull apiKey: String, paginated: Boolean = false) {
        val search = mYouTube.search().list("snippet")

        search.apply {
            key = apiKey
            q = query
            maxResults = MAX_VIDEOS

            if (nextPageToken != null && paginated) {
                pageToken = nextPageToken
            }
        }

        isLoading = true

        Thread(Runnable {
            try {
                val result = search.execute()

                runIfViewAttached { IYoutubeView ->
                    if (result == null) {
                        view.setVideoList(mutableListOf())
                        return@runIfViewAttached
                    } else {
                        nextPageToken = result.nextPageToken

                        if (paginated) {
                            view.appendVideos(result.items)
                        } else {
                            view.setVideoList(result.items)
                        }
                    }
                }
            } catch (error: Exception) {
                runIfViewAttached { iYouTubeSearchView ->
                    when (error) {
                        is UnknownHostException -> view.showAPIError()
                        else -> view.showAPIError()
                    }
                }
            } finally {
                isLoading = false
            }
        }).start()
    }

    fun hasMore() = nextPageToken != null

    fun isLoading() = isLoading

    companion object {
        private const val MAX_VIDEOS = 10L
    }

}