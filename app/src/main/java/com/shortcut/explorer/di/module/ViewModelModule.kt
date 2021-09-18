package com.shortcut.explorer.di.module

import com.shortcut.explorer.business.repositories.ExplainComicRepository
import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.business.repositories.SearchComicsRepository
import com.shortcut.explorer.presentation.SharedViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule{

    @Provides
    @Singleton
    fun provideRecentViewmodel(
        recentComicsRepository: RecentComicsRepository,
        searchComicsRepository: SearchComicsRepository,
        explainComicRepository: ExplainComicRepository
    ) = SharedViewModel(
        recentComicsRepository,
        explainComicRepository,
        searchComicsRepository
    )

}