package com.navtej.doordashlite.view.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.navtej.doordashlite.R;

public class BindingAdapterUtils {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Glide.with(imageView.getContext()).load(url)
                    .fitCenter()
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }
    }
}
