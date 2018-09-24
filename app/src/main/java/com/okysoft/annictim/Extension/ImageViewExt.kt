package com.okysoft.annictim.Extension

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun setImageFromImageUrl(imageView: ImageView, imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        return
    }
    Glide
            .with(imageView.context)
            .load(imageUrl)
            .into(imageView)
}

fun ImageView.setImage(imageUrl: String) {
    Glide
            .with(context)
            .load(imageUrl)
            .into(this)
}