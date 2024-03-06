package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragSignUpPhotoBinding
import co.tiagoaguiar.course.instagram.ui.commons.OurDialog

class SignUpPhotoFrag : Fragment(R.layout.frag_sign_up_photo) {

    private var binding: FragSignUpPhotoBinding? = null

    private var signUpActivity: SignUpActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpPhotoBinding.inflate(layoutInflater)

        val ourDialog = OurDialog(requireContext())

        with(ourDialog) {
            addOptions(R.string.photo, R.string.gallery,) {
                when (it.id) {
                    R.string.photo -> {
                        Log.i("Teste", "foto")
                    }
                    R.string.gallery -> {
                        Log.i("Teste", "galeria")
                    }
                }
            }
            show()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpActivity) {
            signUpActivity = context
        }
    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }
}