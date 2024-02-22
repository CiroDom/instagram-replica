package co.tiagoaguiar.course.instagram.core.commons

import co.tiagoaguiar.course.instagram.core.login.model.FakeDataSource
import co.tiagoaguiar.course.instagram.core.login.model.LoginRepository
import co.tiagoaguiar.course.instagram.core.signup.models.FakeSignUpDataSource
import co.tiagoaguiar.course.instagram.core.signup.models.SignUpRepository

object DependencyInjector {

    fun createLoginRepo() : LoginRepository = LoginRepository(FakeDataSource())

    fun createSignUpRepo() : SignUpRepository = SignUpRepository(FakeSignUpDataSource())

}