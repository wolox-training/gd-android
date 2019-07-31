package ar.com.wolox.android.example.ui.newsDetail

import android.os.Bundle
import android.util.Log
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsDetailActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        Log.wtf("ACTIVITY", "NEWS DETAIL ACTIVITY")

        var fragment = NewsDetailFragment()
        var args = Bundle()
        // args.putSerializable(NEWS, intent.getSerializableExtra(NEWS))
        args.putSerializable(NEWS, args.getSerializable(NEWS))

        fragment.arguments = args

        replaceFragment(R.id.vActivityBaseContent, fragment)
    }

    companion object {
        const val NEWS = "news"
    }
}