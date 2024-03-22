package co.tiagoaguiar.course.instagram.core.signup.presentation

import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.BasePresenter
import co.tiagoaguiar.course.instagram.core.signup.models.SignUpRepository
import co.tiagoaguiar.course.instagram.ui.views.signup.SignUpNameFrag

class SignUpNamePresenter(
    private var view: SignUpNameFrag?,
    private val repository: SignUpRepository) : BasePresenter {

    fun create(email: String, name: String, passw: String, confirmPassw: String, photo: ByteArray? = null) {
        val notValidName = name.length < 2
        val tooShortPassw = passw.length < 8
        val notEqualPassw = passw != confirmPassw

        if (notValidName) {
            view?.displayFailure(R.string.invalid_name, view!!.editName)

            return
        }

        if (tooShortPassw) {
            view?.displayFailure(R.string.invalid_passw_short, view!!.editPassw)

            return
        }

        if (notEqualPassw) {
            view?.displayFailure(R.string.password_not_equal, view!!.editConfirmPassw)

            return
        }

        view?.showProg(true, view!!.createButton)

        repository.create(email, passw, name, photo, object : SignUpCallback {
            override fun onSucess() {
                view?.onSuccCreation(name)
            }

            override fun onFailure(msg: String) {
                view?.onFailure(msg)
            }

            override fun onComplete() {
                view?.showProg(false, view!!.createButton)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}