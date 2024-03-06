package co.tiagoaguiar.course.instagram.ui.interfaces

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.ui.commons.CropperImgFrag
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.commons.OurDialog

interface PhotoChanger {

    private fun getContent(activity: AppCompatActivity, @IdRes idFrag: Int) : ActivityResultLauncher<String> {
        return activity.registerForActivityResult(ActivityResultContracts.GetContent()) {uri ->
            val cropFrag = CropperImgFrag().apply {
                arguments = Bundle().apply {
                    putParcelable(Keys.URI, uri)
                }
            }

            activity as FragReplacer
            activity.replaceFrag(activity, cropFrag, idFrag)
        }
    }

    private fun goToGallery(activity: AppCompatActivity, @IdRes idFrag: Int) {
        getContent(activity, idFrag).launch("image/*")
    }

    fun photoChangerDialog(activity: AppCompatActivity, @IdRes fragId: Int) {
        val ourDialog = OurDialog(activity)

        ourDialog.apply {
            addOptions(R.string.photo, R.string.gallery,) {
                when (it.id) {
                    R.string.photo -> {
                        Log.i("Teste", "foto")
                    }

                    R.string.gallery -> {
                        Log.i("teste", "galeria")
                        goToGallery(activity, R.id.sign_up_frag)
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