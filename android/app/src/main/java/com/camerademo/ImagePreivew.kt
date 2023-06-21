package com.camerademo

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.ViewTreeObserver
import com.camerademo.databinding.ActivityImagePreivewBinding
import java.io.ByteArrayOutputStream

class ImagePreivew : AppCompatActivity() {
    private lateinit var viewBinding: ActivityImagePreivewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityImagePreivewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

//        viewBinding.imagePreview.applyStyle(width = 420.toPixel(resources), height = 213.toPixel(resources))

        val receivedData: Uri? = intent.getParcelableExtra("Image")

        val imageView = viewBinding.imagePreview
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the listener to avoid redundant calls
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // Get the height and width of the ImageView
                val imageViewHeight = imageView.height
                val imageViewWidth = imageView.width

                // Use the obtained dimensions here or store them for later use

//                if (receivedData != null) {
//                    val contentResolver = applicationContext.contentResolver
//                    val inputStream = contentResolver.openInputStream(receivedData)
//                    val bitmap = BitmapFactory.decodeStream(inputStream)
//                    inputStream?.close()
//                    val cropImg = bitmap.cropImage(imageViewWidth, imageViewHeight)
//                    viewBinding.imagePreview.setImageBitmap(cropImg)
//                    viewBinding.imagePreview.rotation = 90F
//                }
            }
        })

//        val imgHeight = viewBinding.imagePreview.layoutParams.height
//        val imgWidth = viewBinding.imagePreview.layoutParams.width

        if (receivedData != null) {
            val contentResolver = applicationContext.contentResolver
            val inputStream = contentResolver.openInputStream(receivedData)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
//            val cropImg = bitmap.cropImage(imageViewWidth, imageViewHeight)
//            viewBinding.imagePreview.setImageBitmap(cropImg)
            viewBinding.documentScanner.setImage(bitmap)

            viewBinding.documentScanner.rotation = 90F
        }


        viewBinding.reTakeImageButton.setOnClickListener {
            onBackPressed()
        }

        viewBinding.doneImageButton.setOnClickListener {
            val baseUrl = convertUriToBase64(receivedData)
            if(baseUrl != null) {
                CameraModule.base64 = baseUrl
            }
            val intent = Intent()
            intent.putExtra("Base64", "")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun convertUriToBase64(uri: Uri?): String? {
        if(uri != null) {
            try {
                val contentResolver: ContentResolver = applicationContext.contentResolver
                val inputStream = contentResolver.openInputStream(uri)

                if (inputStream != null) {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    val buffer = ByteArray(1024)
                    var bytesRead: Int

                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead)
                    }

                    val imageBytes = byteArrayOutputStream.toByteArray()
                    inputStream.close()
                    byteArrayOutputStream.close()

                    val base64String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
                    return base64String
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return null
    }
}