package com.shortcut.explorer.test_util

import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

class TempData {
    companion object {


        val networkError_601 = Resource(status= Status.ERROR, data=null, message=null, errorCode=601)

        fun <T> networkSuccess(content:T?): Response<T> {
            return Response.success(content)
        }

        fun <T> networkError(code:Int=404, content:String="Something went wrong!"): Response<T> {
            val mockType = mockk<MediaType>(relaxed = true)

            return Response.error(code,ResponseBody.create(mockType,content))
        }

        fun getComic(id:Int = System.currentTimeMillis().toInt()): Comic {

            return Comic(id,"sample title", "sample description\nSecond line of description", "test_url","15/01/2020",false)
        }

        fun getComicDto(id:Int = System.currentTimeMillis().toInt()): ComicDto {

            return ComicDto(num = id,
                title = "sample title",
                safe_title = "sample title",
                alt = "sample description\nSecond line of description",
                transcript = "sample description\nSecond line of description",
                img="test_url",
                year="2020",
                month="01",
                day="15",
                news=""
                )
        }

        fun getComics(resultCount:Int = 0) = resultCount
            .times {
                getComic()
            }

        fun getComicDtos(resultCount:Int = 0) = resultCount
            .times {
                getComicDto()
            }

    }


}