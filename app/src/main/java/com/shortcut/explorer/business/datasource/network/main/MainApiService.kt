package com.shortcut.explorer.business.datasource.network.main

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MainApiService {

    @GET("info.0.json")
    suspend fun getLatestComic(): Response<ComicDto>

    @GET("{num}/info.0.json")
    suspend fun getComicByNumber(@Path("num") num:Int): Response<ComicDto>

}