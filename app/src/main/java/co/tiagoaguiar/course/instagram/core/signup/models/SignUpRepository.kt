package co.tiagoaguiar.course.instagram.core.signup.models

import android.net.Uri
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback

class SignUpRepository(
    private val dataSource: FakeSignUpDataSource
) {

    fun passEmail(email: String, callback: SignUpCallback) {
        dataSource.passEmail(email, callback)
    }

    fun create(email: String, passw: String, name: String, photo: ByteArray? = null, callback: SignUpCallback) {
        dataSource.create(email, passw, name, photo, callback)
    }

    fun updatePhoto(photoUri: Uri, callback: SignUpCallback) {
        dataSource.updatePhoto(photoUri, callback)
    }
}