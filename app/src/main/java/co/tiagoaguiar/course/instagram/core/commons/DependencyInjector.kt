package co.tiagoaguiar.course.instagram.core.commons

import co.tiagoaguiar.course.instagram.core.login.model.FakeDataSource
import co.tiagoaguiar.course.instagram.core.login.model.LoginRepository
import co.tiagoaguiar.course.instagram.core.signup.models.FakeSignUpDataSource
import co.tiagoaguiar.course.instagram.core.signup.models.SignUpRepository
import co.tiagoaguiar.course.instagram.core.splash.FakeLocalDataSource
import co.tiagoaguiar.course.instagram.core.splash.SplashRepo

object DependencyInjector {

    fun createSplashRepo(): SplashRepo {
        return SplashRepo(FakeLocalDataSource())
    }

    fun createLoginRepo() : LoginRepository = LoginRepository(FakeDataSource())

    fun createSignUpRepo() : SignUpRepository = SignUpRepository(FakeSignUpDataSource())

}