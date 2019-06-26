package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.example.ExampleActivity
import ar.com.wolox.android.example.ui.example.ExampleFragment
import ar.com.wolox.android.example.ui.login.HomeActivity
import ar.com.wolox.android.example.ui.login.LoginFragment
import ar.com.wolox.android.example.ui.login.RegisterActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun exampleActivity(): ExampleActivity

    @ContributesAndroidInjector
    internal abstract fun exampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun registerActivity(): RegisterActivity

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity
}
