package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.home.HomeFragment
import ar.com.wolox.android.example.ui.login.LoginFragment
import ar.com.wolox.android.example.ui.login.RegisterActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun registerActivity(): RegisterActivity

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment
}
