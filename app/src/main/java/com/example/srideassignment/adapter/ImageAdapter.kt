package com.example.srideassignment.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String?) {
    if (!TextUtils.isEmpty(url)){
        Picasso.with(context).load(url).resize(200, 200).into(this)
    }
}