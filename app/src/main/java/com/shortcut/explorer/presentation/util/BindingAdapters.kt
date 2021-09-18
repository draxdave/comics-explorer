package com.shortcut.explorer.presentation.util

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Download the image behind the url and set it to the [ImageView]
 * @param view : [ImageView] origin.
 * @param imageUrl : [String] url of the image
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty() && imageUrl.toHttpUrlOrNull()!=null) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("html")
fun bindHtmlText(view: TextView, htmlText: String?) {
    if (!htmlText.isNullOrEmpty()) {
        view.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(htmlText)
        }
    }
}