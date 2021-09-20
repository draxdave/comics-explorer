package com.shortcut.explorer.business.domain.model

import com.shortcut.explorer.business.datasource.db.favorites.FavoriteEntity
import java.io.Serializable

data class DetailedComic(
    val pId: Int?,
    val num: Int,
    val title: String,
    val description: String,
    var imgUrl: String?,
    val date: String,
    var explanation: String?,
    var isLast:Boolean
):Serializable

fun DetailedComic.toFavorite():FavoriteEntity{
    return FavoriteEntity(
        num = num,
        pId = pId,
        title = title,
        description = description,
        imgUrl = imgUrl,
        explanation = explanation,
        date = date,
        isLast = false
    )
}