package co.tiagoaguiar.course.instagram.core.signup.presentation

import android.util.Log
import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.BasePresenter
import co.tiagoaguiar.course.instagram.core.signup.models.SignUpRepository
import co.tiagoaguiar.course.instagram.ui.views.signup.SignUpEmailFrag

class SignUpEmailPresenter(
    private var view: SignUpEmailFrag?,
    private val repository: SignUpRepository,) : BasePresenter {
    override fun onDestroy() {
        view = null
    }

    fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (isEmailValid) {
            view?.displayFailure(null, view!!.editEmail)

            repository.create(email, object : SignUpCallback {
                override fun onSucess() {
                    view?.goToNameAndPassw(email)
                }

                override fun onFailure(msg: String) {
                    view?.onEmailFailure(msg)
                }

                override fun onComplete() {
                    view?.showProg(false, view!!.button)
                }

            })
        }
        else {
            view?.displayFailure(R.string.invalid_email, view!!.editEmail)
        }
    }
}