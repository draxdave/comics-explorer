package com.shortcut.explorer.di.module.data

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.datasource.network.search.SearchApiService
import com.shortcut.explorer.business.repositories.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRecentComicsRepository(mainApiService: MainApiService,
                                       networkWrapper: NetworkWrapper
    ):RecentComicsRepository = RecentComicsRepositoryImpl(mainApiService,networkWrapper)

    @Provides
    @Singleton
    fun providesSearchComicsRepository(searchApiService: SearchApiService,
                                       networkWrapper: NetworkWrapper
    ):SearchComicsRepository = SearchComicsRepositoryImpl(searchApiService,networkWrapper)

    @Provides
    @Singleton
    fun providesExplainComicsRepository(searchApiService: SearchApiService,
                                       networkWrapper: NetworkWrapper
    ):ExplainComicRepository = ExplainComicRepositoryImpl(searchApiService,networkWrapper)
}