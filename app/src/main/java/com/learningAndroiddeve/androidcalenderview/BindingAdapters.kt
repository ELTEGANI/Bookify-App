package com.learningAndroiddeve.androidcalenderview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learningAndroiddeve.androidcalenderview.adapter.ChaletAdapter
import com.learningAndroiddeve.androidcalenderview.data.ChaletsProperties



@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("price")
fun TextView.setPrice(chaletsProperties: ChaletsProperties) {
    chaletsProperties.let {
        text = "Price Starting From:"+chaletsProperties.chaletNormalDayPrice.toString()+" "+"USD"
    }
}

