package co.tiagoaguiar.course.instagram.core.signup.presentation

import androidx.annotation.StringRes

interface SignUpCallback {

    fun onSucess()
    fun onFailure(msg: String)
    fun onComplete()

}