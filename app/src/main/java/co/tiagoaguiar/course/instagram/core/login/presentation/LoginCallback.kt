package co.tiagoaguiar.course.instagram.core.login.presentation

import co.tiagoaguiar.course.instagram.core.login.model.UserAuth

interface LoginCallback {

    fun onSucess(userAuth: UserAuth)
    fun onFailure(msg: String)
    fun onComplete()

}