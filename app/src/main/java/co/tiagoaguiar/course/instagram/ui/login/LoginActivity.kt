package co.tiagoaguiar.course.instagram.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

  private val binding by lazy {
      ActivityLoginBinding.inflate(layoutInflater)
  }

  private val buttonEnter by lazy {
      binding.loginButtonEnter
  }

  private val watcher = object : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      /** não há o que fazer aqui*/
    }

    override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
      buttonEnter.isEnabled = c.toString().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {
      /** não há o que fazer aqui*/
    }

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    val inputEmail = binding.loginInputlayEmail
    val editEmail = binding.loginEditEmail
    val inputPassw = binding.loginInputlayPassw
    val editPassw = binding.loginEditPassw
    val buttonEnter = binding.loginButtonEnter

    editEmail.addTextChangedListener(watcher)
    editPassw.addTextChangedListener(watcher)

    buttonEnter.setOnClickListener {

      inputEmail.error = getString(R.string.error_email)

      inputPassw.error = getString(R.string.error_passw)

    }

  }
}