package co.tiagoaguiar.course.instagram.core.commons

import co.tiagoaguiar.course.instagram.core.login.model.Photo
import co.tiagoaguiar.course.instagram.core.login.model.UserAuth
import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "João", "user@user.com", "12345678", null ))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Pedro", "userother@user.com", "123456789", null))

        sessionAuth = usersAuth.first()
    }

}