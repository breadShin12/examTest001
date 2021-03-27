package com.sjh.sample.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sjh.sample.R
import com.sjh.sample.di.NetworkModule

object CustomBindAdapter {
    @BindingAdapter(value = ["android:text"])
    @JvmStatic
    fun setText(textView: TextView, text: String?) {
        textView.text = text.orEmpty()
    }

    @BindingAdapter(value = ["android:text"])
    @JvmStatic
    fun setText(textView: TextView, text: Int) {
        textView.text = text.toString()
    }

    @BindingAdapter(value = ["android:text"])
    @JvmStatic
    fun setText(textView: TextView, text: Float) {
        textView.text = text.toString()
    }

    @BindingAdapter(value = ["android:text"])
    @JvmStatic
    fun setText(textView: TextView, text: Long) {
        textView.text = text.toString()
    }

    @BindingAdapter(value = ["android:weatherTemp"])
    @JvmStatic
    fun setWeatherTempText(textView: TextView, text: Float) {
        val formatText = String.format(textView.context.getString(R.string.weather_temp_format), text.toInt())
        textView.text = formatText
    }

    @BindingAdapter(value = ["android:weatherHumidity"])
    @JvmStatic
    fun setWeatherHumidityText(textView: TextView, text: Int) {
        val formatText = String.format(textView.context.getString(R.string.weather_humidity), text)
        textView.text = formatText
    }

    @BindingAdapter(value = ["android:imgWeatherIcon"])
    @JvmStatic
    fun setImgIv(imageView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imageUrl = NetworkModule.BASE_URL +"/static/img/weather/png/64/$imgUrl.png"
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}