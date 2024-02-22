package co.tiagoaguiar.course.instagram.ui.interfaces

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

interface KeyboardHider {

    fun keyboardHiding(actv: AppCompatActivity, vararg views: View) {
        views.forEach {
            it.setOnClickListener {
                val focusedView = actv.currentFocus
                if (focusedView != null) {
                    val inputMethodManager = actv.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
                }
            }
        }
    }

}