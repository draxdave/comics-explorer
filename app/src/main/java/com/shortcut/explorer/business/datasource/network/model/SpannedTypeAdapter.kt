package com.shortcut.explorer.business.datasource.network.model

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
import com.google.gson.*
import java.lang.reflect.Type

class SpannedTypeAdapter: JsonSerializer<Spanned>, JsonDeserializer<Spanned> {
    override fun serialize(src: Spanned?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.toString())
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?) = des(json.toString())

    fun des(json: String?): Spanned?{
        return json?.parseAsHtml(HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}