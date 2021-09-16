package com.shortcut.explorer.business.datasource.network.search

import com.shortcut.explorer.business.datasource.network.main.ComicDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApiService {

    @GET("wiki/api.php?action=query&list=search&format=json")
    suspend fun search(@Query("srsearch") searchKey : String): Response<SearchDto>

}