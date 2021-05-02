package com.piavillalba.multimediadetail.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.piavillalba.multimediadetail.R

@BindingAdapter("app:loadUrl")
fun bindUrlImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .error(R.drawable.ic_image_preview)
        .placeholder(R.drawable.ic_image_preview)
        .into(view)
}