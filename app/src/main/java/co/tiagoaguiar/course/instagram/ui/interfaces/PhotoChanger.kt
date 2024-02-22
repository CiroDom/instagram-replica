package co.tiagoaguiar.course.instagram.ui.interfaces

import android.content.Context
import android.util.Log
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.ui.commons.OurDialog

interface PhotoChanger {

    fun photoChangerDialog(context: Context) {
        val ourDialog = OurDialog(context)

        with(ourDialog) {
            addOption(R.string.photo, R.string.gallery,) {
                when (it.id) {
                    R.string.photo -> {
                        Log.i("Teste", "foto")
                    }

                    R.string.gallery -> {
                        Log.i("Teste", "galeria")
                    }
                }
            }
            show()
        }
    }
}