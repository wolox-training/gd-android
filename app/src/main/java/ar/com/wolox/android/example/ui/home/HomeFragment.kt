package ar.com.wolox.android.example.ui.home

import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import ar.com.wolox.android.example.ui.news.NewsFragment
import ar.com.wolox.android.example.ui.youtube.YoutubeFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_viewpager.vViewPager
import javax.inject.Inject
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat
import ar.com.wolox.android.R

class HomeFragment @javax.inject.Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject
    internal lateinit var newsFragment: NewsFragment
    @Inject
    internal lateinit var mYoutubeFragment: YoutubeFragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            requireActivity().window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = ContextCompat.getColor(activity!!.baseContext, android.R.color.transparent)
                setBackgroundDrawableResource(R.drawable.gradient_background_wolox)
            }
        }

        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
            Pair<Fragment, String>(newsFragment, getString(R.string.home_news_title)),
            Pair<Fragment, String>(mYoutubeFragment, getString(R.string.home_videos_title)))
        vViewPager.adapter = fragmentPagerAdapter

        vHomeTabs.apply {
            setupWithViewPager(vViewPager)
            getTabAt(NEWS_TAB)!!.setIcon(R.drawable.ic_news_list_on)
            getTabAt(PROFILE_TAB)!!.setIcon(R.drawable.ic_profile_off)

            vHomeTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        NEWS_TAB -> {
                            tab.setIcon(R.drawable.ic_news_list_on)
                        }
                        PROFILE_TAB -> {
                            tab.setIcon(R.drawable.ic_profile_on)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        NEWS_TAB -> {
                            tab.setIcon(R.drawable.ic_news_list_off)
                        }
                        PROFILE_TAB -> {
                            tab.setIcon(R.drawable.ic_profile_off)
                        }
                    }
                }

                override fun onTabReselected(p0: TabLayout.Tab?) {
                    // This method must be empty
                }
            })
        }
    }

    companion object {
        private const val NEWS_TAB = 0
        private const val PROFILE_TAB = 1
    }
}
