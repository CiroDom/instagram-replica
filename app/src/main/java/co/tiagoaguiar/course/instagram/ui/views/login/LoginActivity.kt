package co.tiagoaguiar.course.instagram.ui.views.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import co.tiagoaguiar.course.instagram.core.commons.DependencyInjector
import co.tiagoaguiar.course.instagram.ui.commons.OurTextWatcher
import co.tiagoaguiar.course.instagram.core.login.presentation.LoginPresenter
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider
import co.tiagoaguiar.course.instagram.core.commons.User
import co.tiagoaguiar.course.instagram.ui.views.signup.SignUpActivity

class LoginActivity : AppCompatActivity(), KeyboardHider, User {

  private val binding by lazy {
    ActivityLoginBinding.inflate(layoutInflater)
  }

  private val presenter by lazy {
    LoginPresenter(this, DependencyInjector.createLoginRepo())
  }

  val editEmail by lazy {
    binding.loginEditEmail
  }

  val editPassw by lazy {
    binding.loginEditPassw
  }
  val buttonEnter by lazy {
    binding.loginButtonEnter
  }

  private val txtSignUp by lazy {
    binding.loginTxtSignup
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    keyboardHiding(this, binding.root, binding.loginImgLogo)

    val watcher = OurTextWatcher {
      buttonEnter.isEnabled =
        editEmail.text.toString().isNotEmpty() && editPassw.text.toString().isNotEmpty()
    }

    editEmail.apply {
      addTextChangedListener(watcher)
      addTextChangedListener {
        displayFailure(null, this)
      }
    }
    editPassw.apply {
      addTextChangedListener(watcher)
      addTextChangedListener {
        displayFailure(null, this)
      }
    }

    buttonEnter.setOnClickListener {
      val email = editEmail.text.toString()
      val passw = editPassw.text.toString()

      presenter.login(email, passw)
    }

    txtSignUp.setOnClickListener {
      goToSignUp()
    }

  }

  fun getContext() : Context {
    return this
  }

  private fun goToSignUp() {
    val intent = Intent(this, SignUpActivity::class.java)
    startActivity(intent)
  }
}