package com.shortcut.explorer.business.datasource.network.main

data class ComicDto(
    val num: Int,
    val title: String,
    val alt: String,
    val safe_title: String,
    val transcript: String,
    val img: String,
    val year: String,
    val month: String,
    val day: String,
    val news: String,
)

data class Comic(
    val num: Int,
    val title: String,
    val description: String,
    val imgUrl: String,
    val date: String,
    var isLast:Boolean
)

fun ComicDto.toComic():Comic {

    return Comic(num = num,
        title = safe_title,

        description =
        if (transcript.isBlank()) alt
        else transcript ,

        imgUrl = img,
        date = "$year/$month/$day",
        isLast = false
    )
}