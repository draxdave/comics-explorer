package com.shortcut.explorer.di.module

import com.shortcut.explorer.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}