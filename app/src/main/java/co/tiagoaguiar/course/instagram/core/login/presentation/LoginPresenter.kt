package co.tiagoaguiar.course.instagram.core.login.presentation

import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.BasePresenter
import co.tiagoaguiar.course.instagram.core.login.model.LoginRepository
import co.tiagoaguiar.course.instagram.core.login.model.UserAuth
import co.tiagoaguiar.course.instagram.ui.views.login.LoginActivity

class LoginPresenter(
    private var view: LoginActivity?,
    private val repository: LoginRepository
): BasePresenter {
    fun login(email: String, passw: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswValid = passw.length >= 8

        if (isEmailValid) {
            view?.displayFailure(null, view!!.editEmail)
        }
        else {
            view?.displayFailure(R.string.invalid_email, view!!.editEmail)
        }

        if (isPasswValid) {
            view?.displayFailure(null, view!!.editPassw)
        }
        else {
            view?.displayFailure(R.string.invalid_passw, view!!.editPassw)
        }

        if (isPasswValid && isEmailValid) {
            view?.showProg(true, view!!.buttonEnter)

            repository.login(email, passw, object : LoginCallback {
                override fun onSucess(userAuth: UserAuth) {
                    view?.onUserAuth(view!!)
                }

                override fun onFailure(msg: String) {
                    view?.onUserUnauth(view!!.getContext(), msg)
                }

                override fun onComplete() {
                    view?.showProg(false, view!!.buttonEnter)
                }

            })
        }
    }

    override fun onDestroy() {
        view = null
    }
}