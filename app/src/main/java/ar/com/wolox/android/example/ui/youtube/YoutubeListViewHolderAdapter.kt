package ar.com.wolox.android.example.ui.youtube

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.google.api.services.youtube.model.SearchResult
import kotlinx.android.synthetic.main.viewholder_youtube_list_item.view.*

class YoutubeListViewHolderAdapter(
    private val listener: YoutubeVideoAdapterView
) : RecyclerView.Adapter<YoutubeListViewHolderAdapter.ViewHolder>() {

    private var videoList = ArrayList<SearchResult>()
    private lateinit var view: View
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        Fresco.initialize(context)
        view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_youtube_list_item, parent, false)

        return ViewHolder(view)
    }

    fun setVideos(videos: MutableList<SearchResult>) {
        videoList = ArrayList()
        videoList.addAll(videos)
        Log.wtf("adapter", videoList.get(2).snippet.title)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]

        Log.wtf("BIND", "onBindViewHolder")

        holder.run {
            vYoutubeVideoTitle.text = video.snippet.title
            vYoutubeVideoBody.text = video.snippet.description
            vYoutubeVideoImage.setImageURI(video.snippet.thumbnails.default.url)

            vYoutubeVideoContainer.setOnClickListener {
                listener.onItemClick()
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var vYoutubeVideoBody = item.findViewById<TextView>(R.id.vYoutubeVideoBody)
        var vYoutubeVideoTitle = item.findViewById<TextView>(R.id.vYoutubeVideoTitle)
        var vYoutubeVideoImage = item.findViewById<SimpleDraweeView>(R.id.vYoutubeVideoImage)
        var vYoutubeVideoContainer = item.findViewById<ConstraintLayout>(R.id.vYoutubeVideoContainer)
    }
}