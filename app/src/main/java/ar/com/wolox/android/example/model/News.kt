package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName
import org.joda.time.format.DateTimeFormat
import org.ocpsoft.prettytime.PrettyTime
import java.util.Locale
import java.io.Serializable

data class News(
    var id: Int,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("createdAt")
    var createdAt: String,
    var title: String,
    var picture: String,
    var text: String,
    @SerializedName("likes")
    val likes: List<Int>? = null,
    var like: Boolean = false
) : Serializable {

    fun getTimeFormated(): String {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val dateTime = formatter.parseDateTime(createdAt)
        val prettyTime = PrettyTime(Locale.getDefault())
        val ago = prettyTime.format(dateTime.toDate())
        return ago
    }
}