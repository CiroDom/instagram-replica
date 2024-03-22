package co.tiagoaguiar.course.instagram.ui.views.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation.AnimationListener
import co.tiagoaguiar.course.instagram.core.commons.DependencyInjector
import co.tiagoaguiar.course.instagram.core.splash.SplashPresenter
import co.tiagoaguiar.course.instagram.databinding.ActivitySplashBinding
import co.tiagoaguiar.course.instagram.core.commons.User
import co.tiagoaguiar.course.instagram.ui.views.login.LoginActivity
import co.tiagoaguiar.course.instagram.ui.views.main.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity(), User {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var presenter: SplashPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val repo = DependencyInjector.createSplashRepo()
        presenter = SplashPresenter(this, repo)
        presenter.authenticate()

        binding.splashImg.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    presenter.authenticate()
                }
            })

            duration = 1000 // milli
            alpha(1.0f)
            start()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    fun goToMain() {
        binding.splashImg.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    onUserAuth(this@SplashActivity)
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                }
            })

            duration = 1000 // milli
            startDelay = 500L // milli
            alpha(0.0f)
            start()
        }
    }
    fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}