package com.shortcut.explorer.business.datasource.network.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApiService {

    @GET("wiki/api.php?action=query&list=search&srwhat=text&format=json")
    suspend fun search(@Query("srsearch") searchKey : String): Response<SearchDto>

    @GET("wiki/api.php?action=parse&prop=wikitext&section=1&format=json")
    suspend fun getExplanation(@Query("pageid") pid : Int): Response<ExplainedDto>

}