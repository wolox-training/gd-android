package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName

class NewsLikes(
    @SerializedName("likes")
    val likes: List<Int>? = null
)