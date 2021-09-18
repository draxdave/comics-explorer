package com.shortcut.explorer.business.domain.model

import java.io.Serializable

data class DetailedComic(
    val pId: Int?,
    val num: Int,
    val title: String,
    val description: String,
    var imgUrl: String?,
    val date: String,
    var explanation: String?,
    var isCached:Boolean,
    var isLast:Boolean
):Serializable