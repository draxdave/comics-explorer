package com.shortcut.explorer.business.domain.model

import android.text.Spanned

data class SearchResult(
    val title:String,
    val pageid:Int,
    val num: Int,
    val description: String,
    val date: String
)

fun SearchResult.toDetailedComic() = DetailedComic(
    pId = pageid,
    num = num,
    title = title,
    description = description,
    imgUrl = null,
    date = date,
    explanation = null,
    isCached = false,
    isLast = false
)