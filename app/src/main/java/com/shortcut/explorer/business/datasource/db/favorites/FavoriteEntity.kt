package com.shortcut.explorer.business.datasource.db.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.DetailedComic

@Entity(tableName = "favorite_comics")
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val num: Int,

    val pId: Int?,
    val title: String,
    val description: String,
    var imgUrl: String?,
    val date: String,
    var explanation: String?,
    var isLast:Boolean
)

fun FavoriteEntity.toDetailedComic() = DetailedComic(
    pId = null,
    num = num,
    title = title,
    description = description,
    imgUrl = imgUrl,
    date = date,
    explanation = null,
    isLast = isLast
)

 fun FavoriteEntity.toComic(): Comic {

    return Comic(
        num = num,
        title = title,
        description = description,
        imgUrl = imgUrl,
        date = date,
        isLast = false
    )
}