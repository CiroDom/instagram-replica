package co.tiagoaguiar.course.instagram.core.signup.models

import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback

class SignUpRepository(
    private val dataSource: FakeSignUpDataSource
) {

    fun create(userData: String, callback: SignUpCallback) {
        dataSource.create(userData, callback)
    }

}