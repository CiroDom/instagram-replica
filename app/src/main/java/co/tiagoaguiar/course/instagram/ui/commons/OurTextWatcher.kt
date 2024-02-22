package co.tiagoaguiar.course.instagram.ui.commons

import android.text.Editable
import android.text.TextWatcher

class OurTextWatcher(private val ourOnTextChanged: (String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        ourOnTextChanged(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}