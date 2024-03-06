package co.tiagoaguiar.course.instagram.core.signup.models

import co.tiagoaguiar.course.instagram.core.commons.Database
import co.tiagoaguiar.course.instagram.core.login.model.UserAuth
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpCallback
import java.util.UUID

class FakeSignUpDataSource {
    fun passEmail(email: String, callback: SignUpCallback) {
        val userAuth = Database.usersAuth.firstOrNull {
            email == it.email
        }

        if (userAuth == null) {
            callback.onSucess()
        }
        else {
            callback.onFailure("Usuário já cadastrado")
        }

        callback.onComplete()
    }

    fun create(email: String, passw: String, name: String, photo: ByteArray?, callback: SignUpCallback) {
        val newUser = UserAuth(
            UUID.randomUUID().toString(),
            name,
            email,
            passw,
            photo)
        val wasCreated = Database.usersAuth.add(newUser)

        if (wasCreated) {
            callback.onSucess()
        }
        else {
            callback.onFailure("Erro no servidor")
        }

        callback.onComplete()
    }
}