package com.shortcut.explorer.business.domain.model

data class Comic(
    val num: Int,
    val title: String,
    val description: String,
    val imgUrl: String,
    val date: String,
    var isLast:Boolean
)