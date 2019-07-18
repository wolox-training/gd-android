package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.newsDetail.NewsDetailActivity
import ar.com.wolox.android.example.ui.newsDetail.NewsDetailFragment
import ar.com.wolox.android.example.ui.news.NewsFragment
import ar.com.wolox.android.example.ui.newsDetail.ImageFullScreenActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsModule {

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun newsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun newsDetailFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    internal abstract fun imageFullScreenActivity(): ImageFullScreenActivity
}
