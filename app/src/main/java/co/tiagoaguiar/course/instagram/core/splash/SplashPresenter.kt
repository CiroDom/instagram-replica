package co.tiagoaguiar.course.instagram.core.splash

import co.tiagoaguiar.course.instagram.core.commons.BasePresenter
import co.tiagoaguiar.course.instagram.ui.views.splash.SplashActivity

class SplashPresenter(
    private var view: SplashActivity?,
    private val repo: SplashRepo,
): BasePresenter {

    fun authenticate() {
        repo.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMain()
            }

            override fun onFailure() {
                view?.goToLogin()
            }

        })
    }
    override fun onDestroy() {
        view = null
    }
}