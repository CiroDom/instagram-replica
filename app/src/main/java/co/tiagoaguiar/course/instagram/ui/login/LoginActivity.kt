package co.tiagoaguiar.course.instagram.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

  private val binding by lazy {
      ActivityLoginBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }
}