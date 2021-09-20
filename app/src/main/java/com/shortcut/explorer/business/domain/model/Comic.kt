package com.shortcut.explorer.business.domain.model

import android.text.Spanned

data class Comic(
    val num: Int,
    val title: String,
    val description: String,
    val explanation: String?,
    val imgUrl: String?,
    val date: String,
    var isLast:Boolean
)

fun Comic.toDetailedComic() = DetailedComic(
    pId = null,
    num = num,
    title = title,
    description = description,
    imgUrl = imgUrl,
    date = date,
    explanation = explanation,
    isLast = isLast
)