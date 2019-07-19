package ar.com.wolox.android.example.network

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.NewsLikes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NewsService {

    @GET("/news")
    fun getNews(): Call<List<News>>

    @GET("/news/{id}")
    fun getPostById(@Path("id") id: Int): Call<News>

    @PATCH("/news/{id}")
    fun toogleLike(@Body newsLikes: NewsLikes, @Path("id") newsId: Int): Call<News>
}