package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.DependencyInjector
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpEmailPresenter
import co.tiagoaguiar.course.instagram.databinding.FragSignUpEmailBinding
import co.tiagoaguiar.course.instagram.databinding.FragSignUpNameBinding
import co.tiagoaguiar.course.instagram.ui.commons.LoadingButton
import co.tiagoaguiar.course.instagram.ui.commons.OurTextWatcher
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider
import co.tiagoaguiar.course.instagram.ui.interfaces.PhotoChanger

class SignUpNameFrag : Fragment(R.layout.frag_sign_up_name), PhotoChanger {

    private var binding: FragSignUpNameBinding? = null

    private var fragAttListener: SignUpFragAttListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpNameBinding.bind(view)

        binding?.signUpImg!!.setOnClickListener {
            photoChangerDialog(requireContext())
        }
    }

    override fun onDestroy() {
        binding = null
        fragAttListener = null
        super.onDestroy()
    }
}