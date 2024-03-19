package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragSignUpPhotoBinding
import co.tiagoaguiar.course.instagram.core.commons.PhotoChanger

class SignUpPhotoFrag : Fragment(R.layout.frag_sign_up_photo) {

    private var binding: FragSignUpPhotoBinding? = null

    private var signUpActivity: SignUpActivity? = null

    private var photoChanger: PhotoChanger? = PhotoChanger()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpActivity) {
            signUpActivity = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragSignUpPhotoBinding.inflate(layoutInflater)

        photoChanger?.photoChangerDialog(signUpActivity as AppCompatActivity, signUpActivity!!.getContent, signUpActivity!!.getCamera, signUpActivity!!::getUri)

    }

    override fun onDestroy() {
        binding = null
        photoChanger = null
        super.onDestroy()
    }
}