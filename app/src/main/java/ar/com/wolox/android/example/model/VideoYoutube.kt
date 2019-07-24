package ar.com.wolox.android.example.model

import java.io.Serializable

class VideoYoutube(
    var videoId: Int,
    var title: String,
    var description: String,
    var image: String
) : Serializable
