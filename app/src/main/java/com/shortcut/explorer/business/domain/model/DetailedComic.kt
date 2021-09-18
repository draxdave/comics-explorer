package com.shortcut.explorer.business.domain.model

import android.text.Spanned

data class DetailedComic(
    val pId: Int?,
    val num: Int,
    val title: String,
    val description: Spanned,
    var imgUrl: String?,
    val date: String,
    var explanation: Spanned?,
    var isCached:Boolean,
    var isLast:Boolean
)