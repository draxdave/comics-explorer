package com.shortcut.explorer.business.domain.model

data class SearchResult(
    val title:String,
    val pageid:Int,
    val num: Int,
    val description: String,
    val date: String
)