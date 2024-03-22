package co.tiagoaguiar.course.instagram.core.signup.presentation

import android.net.Uri
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

    fun create(email: String, photo: ByteArray? = null) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (isEmailValid) {
            view?.displayFailure(null, view!!.editEmail)

            repository.passEmail(email, object : SignUpCallback {
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

    fun updatePhoto(photoUri: Uri) {
        view?.showProg(true, view!!.button)

        repository.updatePhoto(photoUri, object : SignUpCallback {
            override fun onSucess() {
                view?.onUpdateSucc()
            }

            override fun onFailure(msg: String) {
                view?.onUpdateFail(msg)
            }

            override fun onComplete() {
                view?.onUpdateComplete()
            }

        })
    }
}