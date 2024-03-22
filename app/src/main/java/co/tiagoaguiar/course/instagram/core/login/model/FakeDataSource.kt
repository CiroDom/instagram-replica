package co.tiagoaguiar.course.instagram.core.login.model

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.core.commons.Database
import co.tiagoaguiar.course.instagram.core.login.presentation.LoginCallback

class FakeDataSource : LoginDataSource {
    override fun login(email: String, passw: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                email == it.email
            }

            if (userAuth == null) {
                callback.onFailure("Usuário não encontrado")
            }
            else if (userAuth.passw != passw) {
                callback.onFailure("Senha incorreta")
            }
            else {
                Database.sessionAuth = userAuth
                callback.onSucess(userAuth)
            }

            callback.onComplete()
        }, 2000)
    }
}