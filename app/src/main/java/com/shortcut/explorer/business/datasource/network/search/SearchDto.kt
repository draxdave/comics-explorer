package com.shortcut.explorer.business.datasource.network.search

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
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
    val timestamp:String
)

fun SearchResult.toQuery(): com.shortcut.explorer.business.domain.model.SearchResult?{
    val divider = ":"

    if (title.contains(divider)) {
        val numAndTitle = title.split(divider)
        val num = numAndTitle
            .first()
            .toInt()
        val title = numAndTitle[1]

        val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())

        val date = dateFormat.format(Date.from(Instant.parse(timestamp)))

        return com.shortcut.explorer.business.domain.model.SearchResult(
            num = num,
            title = title,
            pageid = pageid,
            description = snippet,
            date = date,
        )

    } else{
        return null
    }
}