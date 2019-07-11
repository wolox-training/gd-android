package ar.com.wolox.android.example.ui.home

import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import ar.com.wolox.android.example.ui.news.NewsFragment
import ar.com.wolox.android.example.ui.profile.ProfileFragment
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
    internal lateinit var profileFragment: ProfileFragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    private val newsTab = 0
    private val profileTab = 1

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity!!.window.statusBarColor = ContextCompat.getColor(activity!!.baseContext, android.R.color.transparent)
            activity!!.window.setBackgroundDrawableResource(R.drawable.gradient_background_wolox)
        }

        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(newsFragment, getString(R.string.home_news_title)),
                Pair<Fragment, String>(profileFragment, getString(R.string.home_profile_title)))
        vViewPager.adapter = fragmentPagerAdapter

        vTabs.setupWithViewPager(vViewPager)

        vTabs.getTabAt(newsTab)!!.setIcon(R.drawable.ic_news_list_on)
        vTabs.getTabAt(profileTab)!!.setIcon(R.drawable.ic_profile_off)

        vTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    newsTab -> {
                        tab.setIcon(R.drawable.ic_news_list_on)
                    }
                    profileTab -> {
                        tab.setIcon(R.drawable.ic_profile_on)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    newsTab -> {
                        tab.setIcon(R.drawable.ic_news_list_off)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_profile_off)
                    }
                }
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
                //
            }
        })
    }
}