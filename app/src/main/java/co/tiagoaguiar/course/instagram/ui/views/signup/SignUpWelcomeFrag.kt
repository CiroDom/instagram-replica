package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.Database
import co.tiagoaguiar.course.instagram.databinding.FragSignUpWelcomeBinding
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import java.lang.IllegalArgumentException

class SignUpWelcomeFrag : Fragment(R.layout.frag_sign_up_welcome) {

    private var binding: FragSignUpWelcomeBinding? = null

    private var signUpActivity: SignUpActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpWelcomeBinding.bind(view)

        val name = arguments?.getString(Keys.NAME_SIGN_UP)
            ?: throw IllegalArgumentException("name not found")

        with(binding!!){
            signUpTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

            signUpButtonNextName.apply {
                isEnabled = true
                setOnClickListener {
                    if (Database.sessionAuth?.photo == null) {

                    }
                    else {

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        binding = null
        signUpActivity = null
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpActivity) {
            signUpActivity = context
        }
    }
}