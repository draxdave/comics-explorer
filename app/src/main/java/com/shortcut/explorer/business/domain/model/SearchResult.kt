package com.shortcut.explorer.business.domain.model

import android.text.Spanned

data class SearchResult(
    val title:String,
    val pageid:Int,
    val num: Int,
    val description: Spanned,
    val date: String
)