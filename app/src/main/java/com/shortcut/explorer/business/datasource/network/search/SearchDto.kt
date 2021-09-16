package com.shortcut.explorer.business.datasource.network.search

import com.shortcut.explorer.business.domain.model.Comic
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

data class SearchDto(val query:Query)

data class Query(val search:List<SearchResult>)

data class SearchResult(
    val title:String,
    val pageid:Int,
    val wordcount:Int,
    val snippet:String,
    val timestamp:Instant
)

fun SearchResult.toComic():Comic{
    val numAndTitle = title.split(":")
    val num = numAndTitle
        .first()
        .toInt()
    val title = numAndTitle[1]

    val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())

    val date = dateFormat.format(Date.from(timestamp))

    return Comic(
        num = num,
        title = title,
        description = snippet,
        imgUrl = "",
        date = date,
        isLast = false
    )
}