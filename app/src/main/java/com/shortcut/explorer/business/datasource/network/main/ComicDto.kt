package com.shortcut.explorer.business.datasource.network.main

import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.SearchResult

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

fun ComicDto.toComic(): Comic {

    val parsedDescription =
        if (transcript.isBlank()) alt
        else transcript


    return Comic(
        num = num,
        title = title,
        description = parsedDescription,
        explanation = null,
        imgUrl = img,
        date = "$year/$month/$day",
        isLast = false
    )
}

fun ComicDto.toSearchResult(): SearchResult {

    val parsedDescription =
        if (transcript.isBlank()) alt
        else transcript


    return SearchResult(
        num = num,
        pageid = null,
        title = title,
        description = parsedDescription,
        date = "$year/$month/$day"
    )
}