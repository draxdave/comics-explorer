package com.shortcut.explorer.di.module

import com.shortcut.explorer.presentation.MainActivity
import com.shortcut.explorer.presentation.recent.RecentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeRecentFragmentInjection(): RecentFragment
}