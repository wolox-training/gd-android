package ar.com.wolox.android.example.ui.youtube

import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.VideoYoutube
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import javax.inject.Inject

class YoutubeOpenVideoActivity : WolmoActivity(), YouTubePlayer.OnInitializedListener {

    @Inject lateinit var toastFactory: ToastFactory

    private val videoFragment = YouTubePlayerSupportFragment()

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, videoFragment)
        videoFragment.initialize(getString(R.string.youtube_api_key), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {
        youTubePlayer?.run {
            val resultObject = requiredArguments().getSerializable(VIDEO) as VideoYoutube
            when (resultObject.kind) {
                PLAYLIST -> youTubePlayer.loadPlaylist(resultObject.playlistId)
                VIDEO -> youTubePlayer.loadVideo(resultObject.videoId)
                else -> onNoPlayableResult()
            }
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) = toastFactory.showLong(R.string.unknown_error)

    private fun requiredArguments() = intent.extras!!

    private fun onNoPlayableResult() {
        toastFactory.showLong(R.string.youtube_no_videos_found)
        finish()
    }

    companion object {
        const val VIDEO = "youtubeVideo"
        const val PLAYLIST = "youtubePlaylist"
    }
}