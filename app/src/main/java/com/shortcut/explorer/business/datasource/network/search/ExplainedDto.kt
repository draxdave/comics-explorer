package com.shortcut.explorer.business.datasource.network.search

import com.google.gson.annotations.SerializedName

class ExplainedDto(val parse:Parsed)

data class Parsed(
    val title:String,
    val pageid:Int,
    val wikitext:ParsedWikiText
)

data class ParsedWikiText(
    @SerializedName("*")
    val parsedWikiText:String
)
