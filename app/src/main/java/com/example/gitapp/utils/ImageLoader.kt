package com.example.gitapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class ImageLoader : AppGlideModule() {

    companion object {

        fun loadImage(
            url: String?,
            view: ImageView,
            placeHolder: Int,
            context: Context
        ) {
            if (url == null || url.isEmpty()) {
                return
            }
            val requestOptions = RequestOptions().placeholder(placeHolder).fitCenter()
            Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(view)
        }
    }

}