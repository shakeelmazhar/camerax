package com.camerademo

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.view.PreviewView

fun PreviewView?.applyStyle(width: Int = 500, height: Int = 600) {
// Set dynamic width and height
    this?.apply {
        val viewLayoutParams = layoutParams
        viewLayoutParams.width = width
        viewLayoutParams.height = height

        this.layoutParams = viewLayoutParams
    }

}

fun Int.toPixel(resources: Resources): Int {
    val scale: Float = resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun AppCompatImageView?.applyStyle(width:Int = 420, height:Int = 213){
    this?.apply {
        val viewLayoutParams = layoutParams
        viewLayoutParams.width = width
        viewLayoutParams.height = height
    }
}

fun Bitmap.cropImage(width: Int = 300, height: Int = 600): Bitmap {
    val cropRect = Rect() // Replace with your desired crop rectangle
    return Bitmap.createBitmap(
        this,
        cropRect.left,
        cropRect.top,
        3000,
        3000
    )
}

fun Button?.applyStyle() {
    this?.apply {
// Set dynamic width and height
        val layoutParams = layoutParams
        layoutParams.width = resources.getDimensionPixelSize(R.dimen.dynamic_button_width)
        layoutParams.height = resources.getDimensionPixelSize(R.dimen.dynamic_button_height)
        this.layoutParams = layoutParams

// Set dynamic margin
        val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.bottomMargin =
            resources.getDimensionPixelSize(R.dimen.dynamic_button_margin_bottom)
        marginLayoutParams.marginEnd =
            resources.getDimensionPixelSize(R.dimen.dynamic_button_margin_end)
        this.layoutParams = marginLayoutParams

// Set dynamic elevation
        elevation = resources.getDimension(R.dimen.dynamic_button_elevation)

// Set dynamic text
//        button.text = getString(R.string.dynamic_button_text)

        // Create a GradientDrawable with rounded corners
        val shapeDrawable = GradientDrawable()
        shapeDrawable.shape = GradientDrawable.RECTANGLE
        shapeDrawable.cornerRadius = 16f // Set the desired corner radius in pixels
        background = shapeDrawable
    }
}