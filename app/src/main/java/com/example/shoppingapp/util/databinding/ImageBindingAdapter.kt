package com.example.shoppingapp.util.databinding

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:loadImageFromURI")
    fun loadImageFromUri(imageView: ImageView, uri: Uri) {
        Glide.with(imageView.context).load(uri).centerCrop().into(imageView)
    }
    @JvmStatic
    @BindingAdapter("app:loadImageFromStringURI")
    fun loadImageFromStringURI(imageView: ImageView, stringUri: String) {
        val uri = stringUri.toUri()
        Glide.with(imageView.context).load(uri).centerInside().into(imageView)
    }

}