package co.tiagoaguiar.course.instagram.core.commons

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.ui.commons.CropperImgFrag
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.commons.OurDialog
import co.tiagoaguiar.course.instagram.ui.interfaces.FragReplacer
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoChanger {


    private fun goToGallery(getContent:  ActivityResultLauncher<String>) {
        getContent.launch("image/*")
    }

    @Throws(IOException::class)
    private fun createImageFile(activity: AppCompatActivity) : File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile("${timestamp}_", ".jpg", dir)
    }

    private fun goToCamScreen(activity: AppCompatActivity, getUri: (Uri) -> Unit, getCamera: ActivityResultLauncher<Uri>) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity.packageManager) != null) {
            val photoFile: File? =
                try {
                    createImageFile(activity)
                }
                catch (exception: IOException) {
                    Log.e("RegisterActv", exception.message, exception)
                    null
                }

            photoFile?.also {
                val photoUri = FileProvider.getUriForFile(
                    activity,
                    "co.tiagoaguiar.course.instagram.fileprovider",
                    it
                )

                getUri(photoUri)

                getCamera.launch(photoUri)
            }
        }
    }

    fun photoChangerDialog(
        activity: AppCompatActivity,
        getContent:  ActivityResultLauncher<String>,
        getCamera: ActivityResultLauncher<Uri>,
        getUri: (Uri) -> Unit) {
        val ourDialog = OurDialog(activity)

        ourDialog.apply {
            addOptions(R.string.photo, R.string.gallery,) {
                when (it.id) {
                    R.string.photo -> {
                        goToCamScreen(activity, getUri, getCamera)
                    }

                    R.string.gallery -> {
                        goToGallery(getContent)
                    }
                }
            }

            show()
        }
    }

    fun onCropImgResult(context: Context, uri: Uri?) : Bitmap? {
        if (uri != null) {
            val bitmap =
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                }
                else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }

            return bitmap
        }

        return null
    }
}