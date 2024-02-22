package co.tiagoaguiar.course.instagram.ui.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivitySignUpBinding
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider

class SignUpActivity : AppCompatActivity(), KeyboardHider, SignUpFragAttListener {

    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        keyboardHiding(this, binding.root)

        val emailFrag = SignUpEmailFrag()
        replaceFrag(emailFrag)

        binding.signUpTxtHasAlreadyAccount.setOnClickListener {
            finish()
        }
    }

    private fun replaceFrag(frag: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.sign_up_frag) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.sign_up_frag, frag)
                commit()
            }
        }
        else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.sign_up_frag, frag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun goToNameAndPassw(email: String) {
        val bundle = Bundle()
        bundle.putString(Keys.EMAIL_SIGN_UP, email)

        val nameFrag = SignUpNameFrag()
        nameFrag.arguments = bundle

        replaceFrag(nameFrag)
    }
}