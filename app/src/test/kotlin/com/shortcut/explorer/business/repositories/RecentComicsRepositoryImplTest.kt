@file:Suppress("ClassName")

package com.shortcut.explorer.business.repositories

import app.cash.turbine.test
import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.domain.Constants
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import com.shortcut.explorer.test_util.TempData
import com.shortcut.explorer.test_util.runBlockingTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

internal class `Recent Comics Repository Tested for` {


    private val mockMainApiService = mockk<MainApiService>()
    private val mockNetworkWrapper = NetworkWrapper()

    private val recentComicsRepository = RecentComicsRepositoryImpl(mockMainApiService, mockNetworkWrapper)

    @Nested
    inner class `getting last comic and`{

        @BeforeEach
        fun initBefore(){

        }

        @Test
        fun `network returns empty result`() = runBlockingTest{

            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkSuccess(null)

            val results = mutableListOf<ComicDto>()
            recentComicsRepository.getLastComic{
                results.add(it)
            }

            results shouldHaveSize  0
        }

        @Test
        fun `network returns a result`() = runBlockingTest{
            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkSuccess(TempData.getComicDto())

            val results = mutableListOf<ComicDto>()
            recentComicsRepository.getLastComic{
                results.add(it)
            }

            results shouldHaveSize  1
        }

        @Test
        fun `network returns an error`() = runBlockingTest{

            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkError()

            var isSuccessful = false
            val result = recentComicsRepository.getLastComic{
                isSuccessful = true
            }

            result.status shouldBeEqualTo  Status.ERROR
            isSuccessful shouldBeEqualTo  false

        }
    }

    @Nested
    inner class `getting comic by number and`{

        @BeforeEach
        fun initBefore(){

        }

        @Test
        fun `network returns empty result`() = runBlockingTest{

            coEvery { mockMainApiService.getComicByNumber(any()) } returns TempData.networkSuccess(null)

            val results = mutableListOf<ComicDto>()
            recentComicsRepository.getComicByNumber(0){
                results.add(it)
            }

            results shouldHaveSize  0
        }

        @Test
        fun `network returns a result`() = runBlockingTest{
            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkSuccess(TempData.getComicDto())

            val results = mutableListOf<ComicDto>()
            recentComicsRepository.getComicByNumber(0){
                results.add(it)
            }

            results shouldHaveSize  1
        }

        @Test
        fun `network returns an error`() = runBlockingTest{

            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkError()

            var isSuccessful = false
            val result = recentComicsRepository.getComicByNumber(0){
                isSuccessful = true
            }

            result.status shouldBeEqualTo  Status.ERROR
            isSuccessful shouldBeEqualTo  false

        }
    }

    @ExperimentalTime
    @Nested
    inner class `getting recent comics and`{

        val loading: Resource<ComicDto> =  Resource.loading()
        val emptyResponse = Resource.success(TempData.getComicDtos(0))
        val nonEmptyResponse = Resource.success(TempData.getComicDtos(Constants.RESENT_PAGE_ITEM_COUNT))
        val networkError = TempData.networkError<ComicDto>()

        @BeforeEach
        fun initBefore(){

        }

        @ExperimentalTime
        @Test
        fun `network returns empty result`() = runBlockingTest{

            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkSuccess(null)

            recentComicsRepository.getRecentComics().test {
                awaitItem() shouldBeEqualTo  loading
                awaitItem().data?.size shouldBeEqualTo   emptyResponse.data?.size
                awaitComplete()
            }
        }

        @Test
        fun `network returns a list of results`() = runBlockingTest{
            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkSuccess(TempData.getComicDto(1))
            coEvery { mockMainApiService.getComicByNumber(any()) } returns TempData.networkSuccess(TempData.getComicDto(1))

            recentComicsRepository.getRecentComics().test {
                awaitItem() shouldBeEqualTo  loading
                awaitItem().data?.size shouldBeEqualTo   nonEmptyResponse.data?.size
                awaitComplete()
            }
        }

        @Test
        fun `network returns an error`() = runBlockingTest{

            coEvery { mockMainApiService.getLatestComic() } returns TempData.networkError<ComicDto>()

            recentComicsRepository.getRecentComics().test{
                awaitItem() shouldBeEqualTo  loading
                awaitItem().errorCode shouldBeEqualTo networkError.code()

                awaitComplete()
            }
        }
    }
}