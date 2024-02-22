package co.tiagoaguiar.course.instagram.core.login.model

import co.tiagoaguiar.course.instagram.core.login.presentation.LoginCallback

interface LoginDataSource {

    fun login(email: String, passw: String, callback: LoginCallback)

}