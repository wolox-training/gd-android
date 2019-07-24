package ar.com.wolox.android.example.ui.youtube

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.google.api.services.youtube.model.SearchResult
import kotlinx.android.synthetic.main.fragment_youtube_search.*
import javax.inject.Inject

class YoutubeFragment @Inject constructor() : WolmoFragment<YoutubePresenter>(), IYoutubeView, YoutubeVideoAdapterView {

    @Inject lateinit var mToastFactory: ToastFactory

    private val videoAdapter = YoutubeListViewHolderAdapter(this)

    override fun layout(): Int = R.layout.fragment_youtube_search

    override fun init() {

        vYoutubeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = videoAdapter
        }

        vYoutubeSearchButton.onClickListener {
            if (!vYoutubeSearchBox.text!!.isEmpty()) {
                presenter.onYouTubeSearch(vYoutubeSearchBox.text.toString(), getString(R.string.youtube_api_key))
            } else {
                vYoutubeSearchBox.setError(getString(R.string.login_email_error))
            }
        }
    }

    override fun setVideoList(list: MutableList<SearchResult>) {
        Log.wtf("SETVIDEO", list.get(2).snippet.title)
        videoAdapter.setVideos(list)
        videoAdapter.notifyDataSetChanged()
    }

    override fun showAPIError() {
        vYoutubeRecyclerView.post { mToastFactory.showLong(R.string.news_error_api) }
    }

    override fun onItemClick() {
        Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
    }

    override fun appendResults(videos: MutableList<SearchResult>) {
        Log.wtf("PASO", "AGREGA")
    }
}