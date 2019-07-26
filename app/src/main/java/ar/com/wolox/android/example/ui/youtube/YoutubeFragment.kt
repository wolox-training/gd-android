package ar.com.wolox.android.example.ui.youtube

import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.VideoYoutube
import ar.com.wolox.android.example.utils.DynamicScrollRV
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.google.android.youtube.player.YouTubeIntents
import com.google.api.services.youtube.model.SearchResult
import kotlinx.android.synthetic.main.fragment_youtube_search.*
import javax.inject.Inject

class YoutubeFragment @Inject constructor() : WolmoFragment<YoutubePresenter>(), IYoutubeView, YoutubeVideoAdapterView {

    @Inject lateinit var toastFactory: ToastFactory

    private lateinit var videoAdapter: YoutubeListViewHolderAdapter

    override fun layout(): Int = R.layout.fragment_youtube_search

    override fun init() {

        videoAdapter = YoutubeListViewHolderAdapter(this)

        vYoutubeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            vYoutubeRecyclerView.adapter = videoAdapter
        }

        vYoutubeRecyclerView.addOnScrollListener(object : DynamicScrollRV({
            presenter.onYouTubeSearch(vYoutubeSearchBox.text.toString(),
                    getString(R.string.youtube_api_key), true)
        }) {
            override fun isLoading() = presenter.isLoading()
            override fun hasMore() = presenter.hasMore()
        })

        vYoutubeSearchButton.onClickListener {
            if (!vYoutubeSearchBox.text!!.isEmpty()) {
                presenter.onYouTubeSearch(vYoutubeSearchBox.text.toString(), getString(R.string.youtube_api_key))
            } else {
                vYoutubeSearchBox.setError(getString(R.string.login_email_error))
            }
        }
    }

    override fun setVideoList(list: MutableList<SearchResult>) {
        vYoutubeRecyclerView.post {
            if (list.isEmpty()) {
                toastFactory.showLong(R.string.youtube_no_videos_found)
                videoAdapter.setVideos(mutableListOf())
            } else {
                videoAdapter.setVideos(list)
            }
        }
    }

    override fun appendVideos(videos: MutableList<SearchResult>) {
        vYoutubeRecyclerView.post {
            videoAdapter.appendVideos(videos)
        }
    }

    override fun showAPIError() {
        vYoutubeRecyclerView.post { toastFactory.showLong(R.string.youtube_api_error) }
    }

    override fun onItemClick(@NonNull result: SearchResult) {
        context?.let { startOpenVideoActivity(it, result) }
    }

    fun startOpenVideoActivity(context: Context, result: SearchResult) {
        when (result.id.kind) {
            VIDEO, PLAYLIST -> {
                val videoActivity = Intent(context, YoutubeOpenVideoActivity::class.java)
                videoActivity.putExtra(YoutubeOpenVideoActivity.VIDEO, VideoYoutube(result.id.kind, result.id.playlistId, result.id.videoId))
                startActivity(videoActivity)
            }
            else -> {
                val channelIntent = YouTubeIntents.createChannelIntent(requireContext(), result.id.channelId)
                startActivity(channelIntent)
            }
        }
    }

    companion object YouTubeResultKind {
        const val PLAYLIST = "youtubePlaylist"
        const val VIDEO = "youtubeVideo"
    }
}