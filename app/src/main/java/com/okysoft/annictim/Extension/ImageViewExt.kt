package com.okysoft.annictim.Extension

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image_url")
fun setImageFromImageUrl(imageView: ImageView, imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        return
    }
    Glide
            .with(imageView.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .apply(RequestOptions().dontAnimate())
            .into(imageView)
}

fun ImageView.setImage(imageUrl: String) {
    Glide
            .with(this)
            .load(imageUrl)
            .apply(RequestOptions().dontAnimate())
            .into(this)
}

@BindingAdapter(
        value = [
            "loadImage"
        ],
        requireAll = false
)
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl ?: return
    var options = RequestOptions().dontAnimate().circleCrop()
    Glide
            .with(this)
            .load(imageUrl)
            .apply(options)
            .into(this)

}
