package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.youtube.YoutubeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class YoutubeModule {

    @ContributesAndroidInjector
    internal abstract fun youtubeFragment(): YoutubeFragment
}
