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

        Thread {
            try {
                val result = search.execute()

                runIfViewAttached { IYoutubeView ->
                    if (result == null) {
                        view.setVideoList(mutableListOf())
                    } else {
                        nextPageToken = result.nextPageToken

                        if (paginated) {
                            view.appendResults(result.items)
                        } else {
                            view.setVideoList(result.items)
                        }
                    }
                }
            } catch (error: Exception) {
                Log.wtf("CATCH", "Error: " + error.message)
                runIfViewAttached { iYouTubeSearchView ->
                    when (error) {
                        is UnknownHostException -> view.showAPIError()
                        else -> view.showAPIError()
                    }
                }
            } finally {
                isLoading = false
            }
        }.start()
    }

    fun hasMore() = nextPageToken != null

    fun isLoading() = isLoading

    companion object {
        private const val MAX_VIDEOS = 10L
        private const val TAG = "mYouTube"
    }

/*
    fun getVideos() {
        var video: VideoYoutube = VideoYoutube(1, "titulo 1", "descripcion 1", "http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg")
        var videos: MutableList<VideoYoutube>
        videos = mutableListOf(video, video, video, video)

        view.setVideoList(videos)
    }
    */
}