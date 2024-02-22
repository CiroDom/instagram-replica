package co.tiagoaguiar.course.instagram.core.signup.models

import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback

class SignUpRepository(
    private val dataSource: FakeSignUpDataSource
) {

    fun passEmail(email: String, callback: SignUpCallback) {
        dataSource.passEmail(email, callback)
    }

    fun create(email: String, passw: String, name: String, callback: SignUpCallback) {
        dataSource.create(email, passw, name, callback)
    }
}