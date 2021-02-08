package com.piavillalba.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String, @DrawableRes fallback: Int) {
    Glide.with(this)
        .load(url)
        .error(fallback)
        .placeholder(fallback)
        .into(this)
}
