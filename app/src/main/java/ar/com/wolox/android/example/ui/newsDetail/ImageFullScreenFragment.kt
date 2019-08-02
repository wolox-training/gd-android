package ar.com.wolox.android.example.ui.newsDetail

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.newsList.NewsPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoDialogFragment
import kotlinx.android.synthetic.main.activity_image_full_screen.*
import javax.inject.Inject

class ImageFullScreenFragment @Inject constructor() : WolmoDialogFragment<NewsPresenter?>() {

    override fun layout(): Int = R.layout.activity_image_full_screen

    override fun init() {
        setImage(arguments!!.getString(IMAGE_URL))

        vNewsDetailBackArrow?.setOnClickListener {
            activity!!.finish()
        }
    }

    fun setImage(image: String?) {
        vImageFullScreenImageView.setImageURI(image)
    }

    companion object {
        const val IMAGE_URL = "image_url"
    }
}