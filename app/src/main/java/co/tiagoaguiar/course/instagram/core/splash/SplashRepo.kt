package co.tiagoaguiar.course.instagram.core.splash

class SplashRepo (
    private val splashDataSource: SplashDataSource
) {

    fun session(callback: SplashCallback) {
        splashDataSource.session(callback)
    }

}