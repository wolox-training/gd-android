package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.profile.ProfileFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileModule {

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment
}
