package co.tiagoaguiar.course.instagram.core.login.model

import co.tiagoaguiar.course.instagram.core.login.presentation.LoginCallback

class LoginRepository(
    private val dataSource: LoginDataSource
) {

    fun login(email: String, passw: String, callback: LoginCallback) {
        dataSource.login(email, passw, callback)
    }

}