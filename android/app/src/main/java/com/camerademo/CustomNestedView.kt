package com.camerademo

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.squareup.picasso.Picasso

class CustomNestedView(context: Context, text: String, imageUrl: String) : LinearLayout(context) {
    init {
        orientation = HORIZONTAL
        val textView = TextView(context)
        textView.text = text
        val imageView = ImageView(context)
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
        addView(textView)
        addView(imageView)

        // Set click listener
        setOnClickListener {
            // Handle click event
            Toast.makeText(context, "Nested view clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
