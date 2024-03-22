package co.tiagoaguiar.course.instagram.core.splash

import co.tiagoaguiar.course.instagram.core.commons.Database

class FakeLocalDataSource: SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        }
        else {
            callback.onFailure()
        }
    }
}