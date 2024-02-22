package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.DependencyInjector
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpEmailPresenter
import co.tiagoaguiar.course.instagram.databinding.FragSignUpEmailBinding
import co.tiagoaguiar.course.instagram.ui.commons.LoadingButton
import co.tiagoaguiar.course.instagram.ui.commons.OurTextWatcher
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider
import co.tiagoaguiar.course.instagram.ui.interfaces.PhotoChanger
import co.tiagoaguiar.course.instagram.ui.interfaces.User

class SignUpEmailFrag : Fragment(R.layout.frag_sign_up_email), PhotoChanger, User, SignUpFragAttListener {

    private var binding: FragSignUpEmailBinding? = null

    private var fragAttListener: SignUpFragAttListener? = null

    private lateinit var presenter: SignUpEmailPresenter

    lateinit var editEmail: EditText

    lateinit var button: LoadingButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpEmailBinding.bind(view)

        editEmail = binding!!.signUpEditEmail
        button = binding!!.signUpButtonNextEmail

        val repository = DependencyInjector.createSignUpRepo()
        presenter = SignUpEmailPresenter(this, repository)

        val imgView = binding?.signUpImgIcon

        imgView?.setOnClickListener {
            photoChangerDialog(requireContext())
        }

        val watcher = OurTextWatcher {
            button.isEnabled = editEmail.text.toString().isNotEmpty()
        }
        editEmail.apply {
            addTextChangedListener(watcher)
            addTextChangedListener {
                displayFailure(null, this)
            }
        }

        button.setOnClickListener {
            presenter.create(editEmail.text.toString())
        }
    }

    override fun onDestroy() {
        binding = null
        fragAttListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpFragAttListener) {
            fragAttListener = context
        }
    }


    override fun goToNameAndPassw(email: String) {
        fragAttListener?.goToNameAndPassw(email)
    }

    fun onEmailFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}