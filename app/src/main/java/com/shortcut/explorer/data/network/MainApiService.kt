package com.shortcut.explorer.data.network

import com.shortcut.explorer.data.network.model.ComicItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MainApiService {

    @GET("info.0.json")
    suspend fun getLatestComic(): Response<ComicItem>

    @GET("{num}/info.0.json")
    suspend fun getComicByNumber(@Path("num") num:String): Response<ComicItem>

}