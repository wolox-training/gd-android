package ar.com.wolox.android.example.ui.newsDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.com.wolox.android.R
import kotlinx.android.synthetic.main.activity_image_full_screen.*

class ImageFullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full_screen)

        intent.getStringExtra(IMAGE_URL)?.let { vImageFullScreenImageView.setImageURI(it) }

        vImageFullScreenImageView?.setOnClickListener { finish() }
    }

    companion object {
        const val IMAGE_URL = "image_url"
    }
}