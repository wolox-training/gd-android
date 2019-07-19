package ar.com.wolox.android.example.ui.newsDetail

import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class ImageFullScreenActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        var fragment = ImageFullScreenFragment()
        var args = Bundle()
        args.putString(IMAGE_URL, intent.getStringExtra(IMAGE_URL))

        fragment.arguments = args

        replaceFragment(R.id.vActivityBaseContent,
                fragment)
    }

    companion object {
        const val IMAGE_URL = "image_url"
    }
}