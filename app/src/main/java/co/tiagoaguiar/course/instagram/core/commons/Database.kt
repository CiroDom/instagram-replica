package co.tiagoaguiar.course.instagram.core.commons

import co.tiagoaguiar.course.instagram.core.login.model.UserAuth
import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()

    var sessionAuth: UserAuth? = null
    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "João", "user@user.com", "12345678", ))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Pedro", "userother@user.com", "123456789"))
    }

}