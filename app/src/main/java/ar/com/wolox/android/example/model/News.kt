package ar.com.wolox.android.example.model

data class News(
    var id: Int,
    var userId: Int,
    var createdAt: String,
    var title: String,
    var picture: String,
    var text: String
)