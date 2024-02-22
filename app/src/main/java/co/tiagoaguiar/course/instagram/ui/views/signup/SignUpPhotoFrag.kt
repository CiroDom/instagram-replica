package co.tiagoaguiar.course.instagram.ui.views.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragSignUpPhotoBinding
import co.tiagoaguiar.course.instagram.ui.commons.OurDialog

class SignUpPhotoFrag : Fragment(R.layout.frag_sign_up_photo) {

    private var binding: FragSignUpPhotoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpPhotoBinding.inflate(layoutInflater)

        val ourDialog = OurDialog(requireContext())

        with(ourDialog) {
            addOption(R.string.photo, R.string.gallery,) {
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

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }
}