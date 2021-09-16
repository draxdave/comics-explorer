package com.shortcut.explorer.di.module

import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.presentation.recent.RecentViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule{

    @Provides
    @Singleton
    fun provideRecentViewmodel(recentComicsRepository: RecentComicsRepository) = RecentViewModel(recentComicsRepository)
    
}