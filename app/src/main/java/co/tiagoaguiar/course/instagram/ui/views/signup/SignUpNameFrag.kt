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
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpNamePresenter
import co.tiagoaguiar.course.instagram.databinding.FragSignUpNameBinding
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.commons.LoadingButton
import co.tiagoaguiar.course.instagram.ui.commons.OurTextWatcher
import co.tiagoaguiar.course.instagram.core.commons.PhotoChanger
import co.tiagoaguiar.course.instagram.core.commons.User
import java.lang.IllegalArgumentException

class SignUpNameFrag : Fragment(R.layout.frag_sign_up_name), User {

    private var binding: FragSignUpNameBinding? = null

    private var signUpActivity: SignUpActivity? = null

    private var photoChanger: PhotoChanger? = null

    private lateinit var presenter: SignUpNamePresenter

    private val watcher by lazy {
        OurTextWatcher {
            binding?.signUpButtonNextName!!.isEnabled =
                editName.text.toString().isNotEmpty()
                        && editPassw.text.toString().isNotEmpty()
                        && editConfirmPassw.text.toString().isNotEmpty()
        }
    }

    lateinit var editName: EditText

    lateinit var editPassw: EditText

    lateinit var editConfirmPassw: EditText

    lateinit var createButton: LoadingButton

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpActivity) {
            signUpActivity = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpNameBinding.bind(view)

        val repo = DependencyInjector.createSignUpRepo()
        presenter = SignUpNamePresenter(this, repo)

        binding!!.signUpImg.apply {
            val bitmapFromActv = signUpActivity?.photoInBitmap

            if (bitmapFromActv != null) {
                setImageBitmap(bitmapFromActv)
            }
            setOnClickListener {
                val actv = activity as SignUpActivity
                photoChanger?.photoChangerDialog(actv, actv.getContent, actv.getCamera, actv::getUri)
            }
        }

        editName = binding!!.signUpEditName
        editName.apply {
            addTextChangedListener(watcher)
            addTextChangedListener {
                displayFailure(null, this)
            }
        }

        editPassw = binding!!.signUpEditPassw
        editPassw.apply {
            addTextChangedListener(watcher)
            addTextChangedListener {
                displayFailure(null, this)
            }
        }

        editConfirmPassw = binding!!.signUpEditPasswConfirm
        editConfirmPassw.apply {
            addTextChangedListener(watcher)
            addTextChangedListener {
                displayFailure(null, this)
            }
        }

        createButton = binding!!.signUpButtonNextName
        createButton.setOnClickListener {
            val email = arguments?.getString(Keys.EMAIL_SIGN_UP)
                ?: throw IllegalArgumentException("email not found")
            val name = editName.text.toString()
            val passw = editPassw.text.toString()
            val confirmPassw = editConfirmPassw.text.toString()

            presenter.create(email, name, passw, confirmPassw)
        }
    }

    override fun onDestroy() {
        binding = null
        signUpActivity = null
        photoChanger = null
        presenter.onDestroy()
        super.onDestroy()
    }

    fun onFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun onSuccCreation(name: String) {
        signUpActivity?.goToMain()
    }
}