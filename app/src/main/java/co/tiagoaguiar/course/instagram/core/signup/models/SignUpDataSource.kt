package co.tiagoaguiar.course.instagram.core.signup.models

import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback

interface SignUpDataSource {

    fun create(userData: String, callback: SignUpCallback)

}