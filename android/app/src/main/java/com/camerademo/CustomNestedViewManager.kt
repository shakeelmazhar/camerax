package com.camerademo

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.squareup.picasso.Picasso

class CustomNestedViewManager : SimpleViewManager<CustomNestedView>() {

    companion object {
        const val REACT_CLASS = "CustomNestedView"
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(reactContext: ThemedReactContext): CustomNestedView {
        return CustomNestedView(reactContext, "", "")
    }

    @ReactProp(name = "text")
    fun setText(view: CustomNestedView, text: String) {
        // Set the text prop on the CustomNestedView
        val textView = view.getChildAt(0) as TextView
        textView.text = text
    }

    @ReactProp(name = "imageUrl")
    fun setImageUrl(view: CustomNestedView, imageUrl: String) {
        // Set the imageUrl prop on the CustomNestedView
        val imageView = view.getChildAt(1) as ImageView
        Picasso.get().load(imageUrl).into(imageView)
    }
}
