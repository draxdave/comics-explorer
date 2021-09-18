package com.shortcut.explorer.business.datasource.network.main

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
import com.shortcut.explorer.business.domain.model.Comic

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
        description = parsedDescription.parseAsHtml(HtmlCompat.FROM_HTML_MODE_LEGACY),
        imgUrl = img,
        date = "$year/$month/$day",
        isLast = false
    )
}