package com.camerademo

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.camerademo.databinding.ImagePreviewBinding

class PreviewImageActivity : AppCompatActivity() {
    private lateinit var viewBinding: ImagePreviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ImagePreviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val imageUri: Uri? = intent.getParcelableExtra("Image")
        if (imageUri != null) {
            showCropImageView()
            val contentResolver: ContentResolver = applicationContext.contentResolver

        // Use the ContentResolver to openInputStream and decode the bitmap
            val inputStream = contentResolver.openInputStream(imageUri)
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            viewBinding.documentScanner.setImage(bitmap)
            viewBinding.documentScanner.rotation = 90f

        }
        // Request camera permissions

        // Set up the listeners for take photo and video capture buttons

        attachClickListeners()
    }


    private fun attachClickListeners(){
        viewBinding.apply {
            doneCropImageButton.setOnClickListener {
                visibleFinalPreviewView()
                hideCropView()
                setCroppedImageToImageView(documentScanner.getCroppedImage())
            }

            cancelCropImageButton.setOnClickListener {
                hideFinalPreviewView()
                onBackPressed()
            }

            doneImageButton.setOnClickListener {
                // do your work
                Toast.makeText(this@PreviewImageActivity,"WILL DO SOMETHING IN HERE", Toast.LENGTH_LONG).show()
            }

            reTakeImageButton.setOnClickListener {
                onBackPressed()
            }
            documentScanner.setOnLoadListener { loading ->
                progressBar.isVisible = loading
            }
        }

    }

    private fun setCroppedImageToImageView(croppedImage: Bitmap) {
        viewBinding.apply {
            imagePreview.setImageBitmap(croppedImage)
            imagePreview.rotation = 90f
        }
    }

    private fun visibleFinalPreviewView(){
        viewBinding.apply {
            finalImagePreviewRL.visibility = View.VISIBLE
        }
    }

    private fun hideFinalPreviewView(){
        viewBinding.apply {
            finalImagePreviewRL.visibility = View.GONE
        }
    }

    private fun showCropImageView(){
        viewBinding.apply {
            cropImageRL.visibility = View.VISIBLE
        }
    }
    private fun hideCropView(){
        viewBinding.apply {
            cropImageRL.visibility = View.GONE
        }
    }

}