package com.shortcut.explorer.di.module

import com.shortcut.explorer.presentation.MainActivity
import com.shortcut.explorer.presentation.details.ComicDetailsFragment
import com.shortcut.explorer.presentation.favorite.FavoritesFragment
import com.shortcut.explorer.presentation.recent.RecentFragment
import com.shortcut.explorer.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.InternalCoroutinesApi

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeRecentFragmentInjection(): RecentFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragmentInjection(): FavoritesFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragmentInjection(): SearchFragment

    @InternalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun contributeDetailsFragmentInjection(): ComicDetailsFragment
}