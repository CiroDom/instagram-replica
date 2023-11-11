package co.tiagoaguiar.course.instagram.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.databinding.FragSignUpNameBinding
import co.tiagoaguiar.course.instagram.databinding.FragSignUpPhotoBinding
import co.tiagoaguiar.course.instagram.databinding.FragSignUpWelcomeBinding

class SignUpPhotoFrag : Fragment() {

    private val binding by lazy {
        FragSignUpPhotoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}