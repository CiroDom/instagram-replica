package co.tiagoaguiar.course.instagram.core.signup.models

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.core.commons.Database
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback

class FakeSignUpDataSource : SignUpDataSource {
    override fun create(userData: String, callback: SignUpCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                userData == it.email
            }

            if (userAuth == null) {
                callback.onSucess()
            }
            else {
                callback.onFailure("Usuário já cadastrado")
            }

            callback.onComplete()
        }, 2000)
    }
}